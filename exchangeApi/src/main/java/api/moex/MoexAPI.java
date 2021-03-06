package api.moex;

import api.interfaces.IApi;
import api.models.HistoryEntry;
import api.models.moex.MoexHistoryHistoryDataJson;
import api.models.moex.MoexHistoryJson;
import api.models.moex.MoexSecurity;
import api.models.moex.MoexSecurityJson;
import api.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoexAPI implements IApi {
    private static final String BASE_URL = "https://iss.moex.com";

    private enum ColumnsHistory {
        TRADEDATE,
        CLOSE,
        HIGH,
        LOW,
        VOLUME,
    }

    enum ColumnsSecurity {
        boardid,
        market,
        engine,
        is_primary,
    }

    @Override
    public List<HistoryEntry> getHistory(String ticker) throws URISyntaxException, IOException, InterruptedException, ParseException {
        final var security = getSecurityParameters(ticker);

        var start = 0;
        final var jsonHistoryAccumulator = new ArrayList<MoexHistoryHistoryDataJson>();
        final var mapper = new ObjectMapper();
        while (true) {
            final var uri = getSecurityHistoryUri(ticker, security, start);
            final var response = Utils.httpGetUrl(uri);
            final var jsonHistory = mapper.readValue(response, MoexHistoryJson.class);
            if (jsonHistory.history.data.size() == 0) {
                break;
            }
            jsonHistoryAccumulator.addAll(jsonHistory.history.data);
            start += 100;
        }

        final var historyEntries = new HistoryEntry[jsonHistoryAccumulator.size()];
        for (int i = 0; i < jsonHistoryAccumulator.size(); i++) {
            final var entry = jsonHistoryAccumulator.get(i);
            final var unixtime = getUnixTime(entry.tradeDate);
            historyEntries[i] = new HistoryEntry(unixtime, entry.close, entry.high, entry.low, entry.volume);
        }
        return Arrays.asList(historyEntries);
    }

    private long getUnixTime(String stringDate) throws ParseException {
        final var format = new SimpleDateFormat("yyyy-MM-dd");
        final var date = format.parse(stringDate);
        return date.getTime() / 1000L;
    }

    private URI getSecurityParametersUri(String ticker) throws URISyntaxException {
        final var stringUri = String.format(
                "%s/iss/securities/%s.json?iss.only=boards&iss.meta=off&boards.columns=%s,%s,%s,%s",
                MoexAPI.BASE_URL,
                ticker,
                ColumnsSecurity.boardid.name(),
                ColumnsSecurity.market.name(),
                ColumnsSecurity.engine.name(),
                ColumnsSecurity.is_primary.name()
        );
        return new URI(stringUri);
    }

    private String[] getRanges() {
        final var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new String[]{"2015-01-01", LocalDateTime.now().format(dateFormatter)};
    }

    private URI getSecurityHistoryUri(String ticker, MoexSecurity security, int start) throws URISyntaxException {
        final var ranges = getRanges();
        final var stringUri = String.format(
                "%s/iss/history/engines/%s/markets/%s/boards/%s/securities/%s.json?from=%s&till=%s&start=%d&iss.meta=off&history.columns=%s,%s,%s,%s,%s",
                MoexAPI.BASE_URL,
                security.engine,
                security.market,
                security.board,
                ticker,
                ranges[0],
                ranges[1],
                start,
                ColumnsHistory.TRADEDATE.name(),
                ColumnsHistory.CLOSE.name(),
                ColumnsHistory.HIGH.name(),
                ColumnsHistory.LOW.name(),
                ColumnsHistory.VOLUME.name()
        );
        return new URI(stringUri);
    }

    private MoexSecurity getSecurityParameters(String ticker) throws URISyntaxException, IOException, InterruptedException {
        final var uri = this.getSecurityParametersUri(ticker);
        final var response = Utils.httpGetUrl(uri);
        final var mapper = new ObjectMapper();
        final var jsonSecurity = mapper.readValue(response, MoexSecurityJson.class);
        return jsonSecurity.getPrimary();
    }

}

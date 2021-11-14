package api.moex;

import api.HistoryEntry;
import api.IApi;
import api.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoexAPI implements IApi {
    private static final String BASE_URL = "https://iss.moex.com";

    private enum Columns {
        TRADEDATE,
        CLOSE,
        HIGH,
        LOW,
        VOLUME,
    }

    @Override
    public List<HistoryEntry> getHistory(String ticker) throws URISyntaxException, IOException, InterruptedException, ParseException {
        final var security = getSecurityParameters(ticker);

        var start = 0;
        final var jsonHistoryAccumulator = new ArrayList<>();
        while (true) {
            final var uri = getSecurityHistoryUri(ticker, security, start);
            final var response = Utils.httpGetUrl(uri);
            final var mapper = new ObjectMapper();
            final var jsonHistory = mapper.readValue(response, MoexHistoryJson.class);
            if (jsonHistory.history.data.size() == 0) {
                break;
            }
            jsonHistoryAccumulator.addAll(jsonHistory.history.data);
            start += 100;
        }

        final var historyEntries = new ArrayList<HistoryEntry>(jsonHistoryAccumulator.size());
        for (Object object : jsonHistoryAccumulator) {
            //noinspection unchecked
            final var list = (ArrayList<String>) object;
            final var unixtime = getUnixTime(list.get(Columns.TRADEDATE.ordinal()));
            final var historyEntry = new HistoryEntry(
                    unixtime,
                    Double.parseDouble(list.get(Columns.CLOSE.ordinal())),
                    Double.parseDouble(list.get(Columns.HIGH.ordinal())),
                    Double.parseDouble(list.get(Columns.LOW.ordinal())),
                    Long.parseLong(list.get(Columns.VOLUME.ordinal()))
            );
            historyEntries.add(historyEntry);
        }
        return historyEntries;
    }

    long getUnixTime(String stringDate) throws ParseException {
        final var format = new SimpleDateFormat("yyyy-MM-dd");
        final var date = format.parse(stringDate);
        return date.getTime() / 1000L;
    }

    URI getSecurityParametersUri(String ticker) throws URISyntaxException {
        final var stringUri = String.format(
                "%s/iss/securities/%s.json?iss.only=boards&iss.meta=off&boards.columns=boardid,market,engine,is_primary",
                MoexAPI.BASE_URL,
                ticker
        );
        return new URI(stringUri);
    }

    String[] getRanges() {
        final var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new String[]{"2015-01-01", LocalDateTime.now().format(dateFormatter)};
    }

    URI getSecurityHistoryUri(String ticker, MoexSecurity security, int start) throws URISyntaxException {
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
                Columns.TRADEDATE.name(),
                Columns.CLOSE.name(),
                Columns.HIGH.name(),
                Columns.LOW.name(),
                Columns.VOLUME.name()
        );
        return new URI(stringUri);
    }

    MoexSecurity getSecurityParameters(String ticker) throws URISyntaxException, IOException, InterruptedException {
        final var uri = this.getSecurityParametersUri(ticker);
        final var response = Utils.httpGetUrl(uri);
        final var mapper = new ObjectMapper();
        final var jsonSecurity = mapper.readValue(response, MoexSecurityJson.class);
        return jsonSecurity.getPrimary();
    }

}

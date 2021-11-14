package api.spbex;

import api.HistoryEntry;
import api.IApi;
import api.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SpbexAPI implements IApi {
    private static final String BASE_URL = "https://investcab.ru/api";

    @Override
    public List<HistoryEntry> getHistory(String ticker) throws URISyntaxException, IOException, InterruptedException {
        final var uri = getUriDayResolution(ticker);

        final var response = Utils.httpGetUrl(uri);
        // escaped json workaround
        final var jsonRaw = response.replace("\\", "");

        final var mapper = new ObjectMapper();
        final var jsonHistory = mapper.readValue(jsonRaw.substring(1, jsonRaw.length() - 1), SpbexTickerJson.class);

        final var historyEntries = new ArrayList<HistoryEntry>(jsonHistory.time.size());
        for (int i = 0; i < jsonHistory.time.size(); i++) {
            final var historyEntry = new HistoryEntry(
                    jsonHistory.time.get(i), jsonHistory.close.get(i),
                    jsonHistory.high.get(i), jsonHistory.low.get(i), 0);
            historyEntries.add(historyEntry);
        }
        return historyEntries;
    }

    private long[] getRanges() {
        return new long[]{1434014660L, System.currentTimeMillis() / 1000L};
    }

    URI getUri(String ticker, @SuppressWarnings("SameParameterValue") String resolution,
               long rangeStart, long rangeEnd) throws URISyntaxException {
        final var stringUri = String.format(
                "%s/chistory?symbol=%s&resolution=%s&from=%d&to=%d",
                SpbexAPI.BASE_URL,
                ticker,
                resolution,
                rangeStart,
                rangeEnd
        );
        return new URI(stringUri);
    }

    URI getUriDayResolution(String ticker) throws URISyntaxException {
        final var ranges = getRanges();
        return getUri(ticker, "D", ranges[0], ranges[1]);
    }

}

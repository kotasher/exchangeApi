package api.spbex;

import api.interfaces.IApi;
import api.models.HistoryEntry;
import api.models.spbex.SpbexTickerJson;
import api.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

        final int size = Stream.of(
                jsonHistory.time.size(), jsonHistory.close.size(),
                jsonHistory.high.size(), jsonHistory.low.size()
        ).min(Integer::compare).orElseThrow();
        final var historyEntries = new HistoryEntry[size];
        for (int i = 0; i < size; i++) {
            historyEntries[i] = new HistoryEntry(
                    jsonHistory.time.get(i),
                    jsonHistory.close.get(i),
                    jsonHistory.high.get(i),
                    jsonHistory.low.get(i),
                    0
            );
        }
        return Arrays.asList(historyEntries);
    }

    private long[] getRanges() {
        return new long[]{1434014660L, System.currentTimeMillis() / 1000L};
    }

    private URI getUri(String ticker, @SuppressWarnings("SameParameterValue") String resolution,
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

    private URI getUriDayResolution(String ticker) throws URISyntaxException {
        final var ranges = getRanges();
        return getUri(ticker, "D", ranges[0], ranges[1]);
    }

}

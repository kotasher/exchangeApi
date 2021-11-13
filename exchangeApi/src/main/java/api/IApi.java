package api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IApi {
    List<HistoryEntry> getHistory(String ticker) throws URISyntaxException, IOException, InterruptedException;
}

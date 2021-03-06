package api.interfaces;

import api.models.HistoryEntry;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

public interface IApi {
    List<HistoryEntry> getHistory(String ticker) throws URISyntaxException, IOException, InterruptedException, ParseException;
}

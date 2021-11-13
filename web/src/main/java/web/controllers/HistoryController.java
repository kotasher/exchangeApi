package web.controllers;

import api.HistoryEntry;

import java.util.List;

public interface HistoryController {
    @SuppressWarnings("unused")
    List<HistoryEntry> history(String exchange, String ticker);
}

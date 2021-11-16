package web.controllers;

import api.models.HistoryEntry;

import java.util.List;

public interface HistoryController {
    @SuppressWarnings("unused")
    List<HistoryEntry> history(String exchange, String ticker);
}

package web.controllers;

import api.ApiFactory;
import api.HistoryEntry;
import api.enums.ApiType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.config.Config;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/")
public class HistoryControllerImpl implements HistoryController {
    @Override
    @GetMapping("{exchange}/{ticker}")
    @Cacheable(value = Config.EXCHANGES_CACHE_KEY, key = "#ticker")
    public List<HistoryEntry> history(@PathVariable String exchange,
                                      @PathVariable String ticker) {
        try {
            final var factory = new ApiFactory();
            final var api = factory.createApi(exchange.toUpperCase());
            return api.getHistory(ticker.toLowerCase());
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }
}

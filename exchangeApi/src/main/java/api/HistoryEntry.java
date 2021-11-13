package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryEntry {
    @JsonProperty("date")
    String date;
    @JsonProperty("close")
    double close;
    @JsonProperty("high")
    double high;
    @JsonProperty("low")
    double low;
    @JsonProperty("volume")
    double volume;

    public HistoryEntry(String date, double close, double high, double low, double volume) {
        this.date = date;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "api.HistoryEntry{" +
                "date='" + date + '\'' +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}

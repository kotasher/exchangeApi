package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HistoryEntry {
    @JsonProperty("date")
    final String date;
    @JsonProperty("close")
    final double close;
    @JsonProperty("high")
    final double high;
    @JsonProperty("low")
    final double low;
    @JsonProperty("volume")
    final long volume;

    public HistoryEntry(long unixtime, double close, double high, double low, long volume) {
        this.date = HistoryEntry.convertToHumanTime(unixtime);
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    private static String convertToHumanTime(Long unixtime) {
        final var cal = Calendar.getInstance();
        cal.setTimeInMillis(unixtime * 1000);
        final var dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormatter.format(cal.getTime());
    }

    @Override
    public String toString() {
        return "api.models.HistoryEntry{" +
                "date='" + date + '\'' +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}

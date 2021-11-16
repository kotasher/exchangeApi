package api.models.moex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class MoexHistoryHistoryDataJson {
    @JsonProperty()
    public String tradeDate;
    @JsonProperty()
    public double close;
    @JsonProperty()
    public double high;
    @JsonProperty()
    public double low;
    @JsonProperty()
    public long volume;

    @Override
    public String toString() {
        return "MoexHistoryHistoryData{" +
                "tradeDate='" + tradeDate + '\'' +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}
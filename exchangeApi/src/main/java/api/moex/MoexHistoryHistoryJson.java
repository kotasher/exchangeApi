package api.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoexHistoryHistoryJson {
    @JsonProperty("columns")
    public List<String> columns;
    @JsonProperty("data")
    public List<List<String>> data;

    @Override
    public String toString() {
        return "MoexHistoryHistoryJson{" +
                "columns=" + columns +
                ", data=" + data +
                '}';
    }
}

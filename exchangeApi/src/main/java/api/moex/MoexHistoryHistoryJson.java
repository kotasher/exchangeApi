package api.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoexHistoryHistoryJson {
    @JsonProperty("columns")
    public List<String> columns = null;
    @JsonProperty("data")
    public List<List<String>> data = null;

    @Override
    public String toString() {
        return "MoexHistoryHistoryJson{" +
                "columns=" + columns +
                ", data=" + data +
                '}';
    }
}

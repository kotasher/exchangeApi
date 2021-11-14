package api.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoexHistoryJson {
    @Override
    public String toString() {
        return "MoexHistoryJson{" +
                "history=" + history +
                '}';
    }

    @JsonProperty("history")
    MoexHistoryHistoryJson history;
}

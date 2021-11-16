package api.models.moex;

import api.models.moex.MoexHistoryHistoryJson;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoexHistoryJson {
    @Override
    public String toString() {
        return "MoexHistoryJson{" +
                "history=" + history +
                '}';
    }

    @JsonProperty("history")
    public MoexHistoryHistoryJson history;
}

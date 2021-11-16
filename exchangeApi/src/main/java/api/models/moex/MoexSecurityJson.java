package api.models.moex;

import api.models.moex.MoexSecurity;
import api.models.moex.MoexSecurityBoardsJson;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoexSecurityJson {
    @JsonProperty("boards")
    MoexSecurityBoardsJson boards;

    @Override
    public String toString() {
        return "MoexSecurityJson{" +
                "boards=" + boards +
                '}';
    }

    public MoexSecurity getPrimary() {
        return boards.getPrimary();
    }
}

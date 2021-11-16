package api.models.moex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class MoexSecurityBoardsDataJson {
    @JsonProperty()
    public String boardID;
    @JsonProperty()
    public String market;
    @JsonProperty()
    public String engine;
    @JsonProperty()
    public int isPrimary;

    @Override
    public String toString() {
        return "MoexSecurityBoardsDataJson{" +
                "boardID='" + boardID + '\'' +
                ", market='" + market + '\'' +
                ", engine='" + engine + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}

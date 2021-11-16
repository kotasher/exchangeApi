package api.models.moex;

public class MoexSecurity {
    public final String board;
    public final String market;
    public final String engine;

    @Override
    public String toString() {
        return "MoexSecurity{" +
                "boardID='" + board + '\'' +
                ", market='" + market + '\'' +
                ", engine='" + engine + '\'' +
                '}';
    }

    public MoexSecurity(String boardID, String market, String engine) {
        this.board = boardID;
        this.market = market;
        this.engine = engine;
    }
}

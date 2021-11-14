package api.moex;

public class MoexSecurity {
    String board;
    String market;
    String engine;

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

package api.moex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoexSecurityBoardsJson {
    @JsonProperty("columns")
    public List<String> columns = null;
    @JsonProperty("data")
    public List<List<String>> data = null;

    @Override
    public String toString() {
        return "MoexSecurityBoardsJson{" +
                "columns=" + columns +
                ", data=" + data +
                '}';
    }

    public MoexSecurity getPrimary() {
        int i;
        for (i = 0; i < data.size(); i++) {
            if (data.get(i).get(MoexAPI.ColumnsSecurity.is_primary.ordinal()).equals("1")) {
                break;
            }
        }
        final var result = data.get(i);
        return new MoexSecurity(
                result.get(MoexAPI.ColumnsSecurity.boardid.ordinal()),
                result.get(MoexAPI.ColumnsSecurity.market.ordinal()),
                result.get(MoexAPI.ColumnsSecurity.engine.ordinal())
        );
    }
}

// json example
//{
//        "boards": {
//          "columns": ["boardid", "market", "engine", "is_primary"],
//          "data": [
//              ["TQBR", "shares", "stock", 1],
//              ["EQBR", "shares", "stock", 0],
//              ["SPEQ", "shares", "stock", 0],
//              ["SMAL", "shares", "stock", 0],
//              ["TQDP", "shares", "stock", 0],
//              ["EQDP", "shares", "stock", 0],
//              ["RPMO", "repo", "stock", 0],
//              ["PTEQ", "ndm", "stock", 0],
//              ["MXBD", "moexboard", "stock", 0],
//              ["CLMR", "classica", "stock", 0],
//              ["SOTC", "otc", "stock", 0],
//              ["CLAD", "classica", "stock", 0],
//              ["STMR", "standard", "stock", 0],
//              ["SDMR", "standard", "stock", 0],
//              ["STAD", "standard", "stock", 0],
//              ["SDAD", "standard", "stock", 0],
//              ["STRP", "standard", "stock", 0],
//              ["SDRP", "standard", "stock", 0],
//              ["PSEQ", "ndm", "stock", 0],
//              ["RPMA", "repo", "stock", 0],
//              ["RPEU", "repo", "stock", 0],
//              ["RPUA", "repo", "stock", 0],
//              ["RPEO", "repo", "stock", 0],
//              ["EQRD", "ccp", "stock", 0],
//              ["EQRE", "ccp", "stock", 0],
//              ["EQWP", "ccp", "stock", 0],
//              ["EQWD", "ccp", "stock", 0],
//              ["EQWE", "ccp", "stock", 0],
//              ["EQRP", "ccp", "stock", 0],
//              ["LIQR", "ccp", "stock", 0],
//              ["EQRY", "ccp", "stock", 0],
//              ["PSRY", "ccp", "stock", 0],
//              ["PSRP", "ccp", "stock", 0],
//              ["PSRD", "ccp", "stock", 0],
//              ["PSRE", "ccp", "stock", 0],
//              ["LIQB", "mamc", "stock", 0]
//          ]
//        }
//}
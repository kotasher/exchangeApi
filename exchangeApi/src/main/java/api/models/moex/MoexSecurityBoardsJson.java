package api.models.moex;

import api.models.moex.MoexSecurity;
import api.models.moex.MoexSecurityBoardsDataJson;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MoexSecurityBoardsJson {
    @JsonProperty("columns")
    public List<String> columns;
    @JsonProperty("data")
    public List<MoexSecurityBoardsDataJson> data;

    @Override
    public String toString() {
        return "MoexSecurityBoardsJson{" +
                "columns=" + columns +
                ", data=" + data +
                '}';
    }

    public MoexSecurity getPrimary() {
        final var security = data.stream()
                .filter(e -> e.isPrimary == 1).findFirst().orElseThrow();
        return new MoexSecurity(security.boardID, security.market, security.engine);
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
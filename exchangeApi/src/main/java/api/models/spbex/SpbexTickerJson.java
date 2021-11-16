package api.models.spbex;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpbexTickerJson {
    @JsonProperty("t")
    public List<Long> time;
    @SuppressWarnings("unused")
    @JsonProperty("o")
    public List<Double> open;
    @JsonProperty("h")
    public List<Double> high;
    @JsonProperty("l")
    public List<Double> low;
    @JsonProperty("c")
    public List<Double> close;
    @SuppressWarnings("unused")
    @JsonProperty("s")
    public String status;
}

// json example
//{
//        "t":[1636588800,1636675200],
//        "o":[20.510000,20.860000],
//        "h":[22.640000,22.070000],
//        "l":[20.510000,20.860000],
//        "c":[21.400000,21.900000],
//        "s":"ok"
//}
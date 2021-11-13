package api;

import api.enums.ApiType;
import api.spbex.SpbexAPI;

import java.util.HashMap;

public class ApiFactory {
    private static final HashMap<ApiType, IApi> instances;

    static {
        instances = new HashMap<>();
        instances.put(ApiType.SPBEX, new SpbexAPI());
    }

    public IApi createApi(ApiType apiType) {
        final var instance = instances.getOrDefault(apiType, null);
        if (instance == null) {
            throw new UnsupportedOperationException("Not implemented");
        }
        return instance;
    }

    public IApi createApi(String apiType) {
        final var type = ApiType.valueOf(apiType);
        return createApi(type);
    }
}

package api.stores;

import api.common.ApiClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import api.stores.data.Store;
import api.stores.data.StoresResponse;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

public class StoresApiClient extends ApiClient {
    public StoresApiClient(String baseUrl) {
        super(baseUrl, "/stores");
        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    public List<Store> getStores() throws InvalidResponseException {
        ApiResponse<StoresResponse> response = caller.executeRequest(getRequest(), Method.GET, StoresResponse.class);
        return response.getContent().getStoreList();
    }
}

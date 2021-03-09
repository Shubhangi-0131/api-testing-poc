package api.common;

import io.restassured.mapper.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiClient {
    protected ApiCaller caller;
    protected Logger Log;

    @Getter
    private final ApiRequest request;

    public ApiClient(String baseUrl, String basePath) {
        Log = LoggerFactory.getLogger(this.getClass().getSimpleName());
        request = new CommonApiRequest(baseUrl, basePath);
        caller = new ApiCaller();
        Log.info("Initiated "+ this.getClass().getName()+" instance");
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        request.withObjectMapper(objectMapper);
        caller.withObjectMapper(objectMapper);
    }
}

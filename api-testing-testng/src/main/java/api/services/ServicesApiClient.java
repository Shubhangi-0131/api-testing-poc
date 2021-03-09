package api.services;

import api.common.ApiClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import api.services.data.Service;
import api.services.data.ServiceResponse;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

import java.util.List;

public class ServicesApiClient extends ApiClient {

    public ServicesApiClient(String baseUrl) {
        super(baseUrl, "/services");

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    public List<Service> getServices() throws InvalidResponseException {
        ApiResponse<ServiceResponse> response = caller.executeRequest(getRequest(), Method.GET, ServiceResponse.class);
        return response.getContent().getServices();
    }

    public Service createService(Service service) throws InvalidResponseException {
        var request = getRequest().withBody(service).withHeader("Content-Type", "application/json");
        ApiResponse<Service> response = caller.executeRequest(request, Method.POST, Service.class);
        return response.getContent();
    }

    public List<Service> getServicesByQuery(String query) throws InvalidResponseException {
        String[] queryParams = query.split("=");
        var request = getRequest().withQueryParam(queryParams[0], queryParams[1]);
        ApiResponse<ServiceResponse> response = caller.executeRequest(getRequest(), Method.GET, ServiceResponse.class);
        return response.getContent().getServices();
    }
}


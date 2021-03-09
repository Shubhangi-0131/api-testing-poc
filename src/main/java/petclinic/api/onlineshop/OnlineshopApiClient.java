package petclinic.api.onlineshop;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import petclinic.api.onlineshop.data.OrderSummary;
import petclinic.api.onlineshop.data.Response;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class OnlineshopApiClient extends ApiClient {

    public OnlineshopApiClient(String baseUrl, String basePath) {
        super(baseUrl,basePath);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }
    //Verify status code
    public Integer getStatus() throws InvalidResponseException {

        ApiResponse  response = caller.executeRequest(getRequest(), Method.GET, Response.class);
        return response.getHttpStatusCode();
    }
    //Header verification

    public Headers getHeader(String s) throws InvalidResponseException {

        ApiResponse  response = caller.executeRequest(getRequest(), Method.GET, Response.class);
        return response.getHttpHeaders();

    }
   //GET Product details
    public Response[] getResponses() throws InvalidResponseException {
        ApiResponse<Response[]> response = caller.executeRequest(getRequest(), Method.GET, Response[].class);
        return response.getContent();
    }
    //POST Create product
    public Response createdProduct(Response response) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(response).withHeader("Content-Type", "application/json");
        ApiResponse<Response> response1 = caller.executeRequest(request, Method.POST, Response.class);
        return response1.getContent();
    }


    //GET OrderSummary details
    public ApiResponse<OrderSummary> getOrderSummary() {

        ApiResponse<OrderSummary> response = caller.executeRequest(getRequest(), Method.GET, OrderSummary.class);
        return response;

    }
    //DELETE
    public ApiResponse<Response[]> deleteProduct() throws InvalidResponseException{

        ApiResponse<Response[]> response = caller.executeRequest(getRequest(),Method.DELETE,Response[].class);
        return response;


    }


}






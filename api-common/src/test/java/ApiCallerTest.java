import api.common.ApiCaller;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.CommonApiRequest;
import api.common.exception.InvalidResponseException;
import com.github.tomakehurst.wiremock.WireMockServer;
import data.JsonResponse;
import io.restassured.http.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiCallerTest {
    static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
    }

    @AfterAll
    static void stopDownWireMock() {
        wireMockServer.stop();
    }

    @Test
    public void executeRequest_noExtraPathRequest_shouldReturnResponse() throws InvalidResponseException {
        //Arrange
        wireMockServer.stubFor(get(urlEqualTo("/test")).willReturn(aResponse().withStatus(200).
                withHeader("Content-Type", "application/json").withBody("{\"status\": 1}")));
        ApiRequest apiRequest = new CommonApiRequest("http://localhost:8089", "/test");
        //Act
        ApiCaller apiCaller = new ApiCaller();
        ApiResponse<String> apiResponse = apiCaller.executeRequest(apiRequest, Method.GET, String.class);
        //Assert
        assertThat(apiResponse.getContent()).isEqualTo("{\"status\": 1}");
    }

    @Test
    public void executeRequest_checkJsonResponse_shouldReturnAsObject() throws InvalidResponseException {
        //Arrange
        wireMockServer.stubFor(get(urlEqualTo("/test")).willReturn(aResponse().withStatus(200).
                withHeader("Content-Type", "application/json").withBody("{\"status\": 1}")));
        ApiRequest apiRequest = new CommonApiRequest("http://localhost:8089", "/test");
        //Act
        ApiCaller apiCaller = new ApiCaller();
        ApiResponse<JsonResponse> apiResponse = apiCaller.executeRequest(apiRequest, Method.GET, JsonResponse.class);
        //Assert
        assertThat(apiResponse.getContent()).isNotNull();
        assertThat(apiResponse.getContent().getStatus()).isEqualTo(1);
    }
}

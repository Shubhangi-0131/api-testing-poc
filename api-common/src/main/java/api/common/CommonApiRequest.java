package api.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public class CommonApiRequest implements ApiRequest {

    private RequestSpecification httpRequest;
    private ObjectMapper objectMapper;

    public CommonApiRequest(String baseUri, String basePath) {
        httpRequest = RestAssured.given();
        withBaseUri(baseUri).withBasePath(basePath).withContentType(ContentType.JSON);
    }

    @Override
    public ApiRequest withBaseUri(String baseUri) {
        httpRequest = httpRequest.baseUri(baseUri);
        return this;
    }

    @Override
    public ApiRequest withBasePath(String basePath) {
        httpRequest = httpRequest.basePath(basePath);
        return this;
    }

    @Override
    public ApiRequest withContentType(ContentType contentType) {
        httpRequest = httpRequest.contentType(contentType);
        return this;
    }

    @Override
    public ApiRequest withHeader(String headerName, String headerValue) {
        httpRequest = httpRequest.header(headerName, headerValue);
        return this;
    }

    @Override
    public ApiRequest withQueryParam(String parameterName, Object... parameterValues) {
        httpRequest = httpRequest.queryParam(parameterName, parameterValues);
        return this;
    }

    @Override
    public ApiRequest withObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    @Override
    public ApiRequest withBody(Object requestBody) {
        httpRequest = objectMapper != null ? httpRequest.body(requestBody, objectMapper) : httpRequest.body(requestBody);
        return this;
    }

    @Override
    public RequestSpecification getRequest() {
        return httpRequest;
    }

}
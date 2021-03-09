package api.common;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public interface ApiRequest {

    ApiRequest withBaseUri(String baseUri);

    ApiRequest withBasePath(String basePath);

    ApiRequest withHeader(String headerName, String headerValue);

    ApiRequest withQueryParam(String parameterName, Object... parameterValues);

    ApiRequest withContentType(ContentType contentType);

    ApiRequest withObjectMapper(ObjectMapper objectMapper);

    ApiRequest withBody(Object requestBody);

    RequestSpecification getRequest();
}

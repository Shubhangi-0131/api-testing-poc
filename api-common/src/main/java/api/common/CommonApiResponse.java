package api.common;

import api.common.exception.InvalidResponseException;
import io.restassured.http.Headers;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;

public class CommonApiResponse<T> implements ApiResponse<T> {

    private final Class<T> responseType;
    private ObjectMapper objectMapper;
    private final Response response;
    private T content;

    public CommonApiResponse(Response response, Class<T> responseType) {
        this.response = response;
        this.responseType = responseType;
    }

    public CommonApiResponse(Response response, Class<T> responseType, ObjectMapper objectMapper) {
        this(response, responseType);
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getContent() throws InvalidResponseException {
        if (content == null) {
            if (response.getStatusCode() >= 200 && response.getStatusCode() <= 226) {
                if (responseType == String.class) {
                    content = (T) response.getBody().asString();
                } else {
                    content = objectMapper != null ? response.getBody().as(responseType, objectMapper) : response.getBody().as(responseType);
                }
            }
            if (content == null) {
                throw new InvalidResponseException(String.format("Response Status : %d", response.getStatusCode()));
            }
        }
        return content;
    }

    @Override
    public Integer getHttpStatusCode() {
        return response.getStatusCode();
    }
    @Override
    public Headers getHttpHeaders() {
        return response.getHeaders();
    }
}

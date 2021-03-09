package api.common;

import api.common.exception.InvalidResponseException;
import io.restassured.http.Headers;

public interface ApiResponse<T> {
    T getContent() throws InvalidResponseException;

    Integer getHttpStatusCode();

    Headers getHttpHeaders();
}

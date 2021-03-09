package data;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {
    @SerializedName("status")
    private Integer status;

    public Integer getStatus() {
        return status;
    }
}

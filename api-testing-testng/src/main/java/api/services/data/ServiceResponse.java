package api.services.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class ServiceResponse {

    @Expose
    private int total;

    @SerializedName("data")
    @Expose
    private List<Service> services;

    @Expose
    private int limit;

    @Expose
    private int skip;
}
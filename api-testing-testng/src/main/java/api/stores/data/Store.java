
package api.stores.data;

import com.google.gson.annotations.Expose;
import lombok.Getter;

import java.util.List;

@Getter
public class Store {

    @Expose
    private String address;
    @Expose
    private String address2;
    @Expose
    private String city;
    @Expose
    private String createdAt;
    @Expose
    private String hours;
    @Expose
    private Long id;
    @Expose
    private Double lat;
    @Expose
    private Double lng;
    @Expose
    private String name;
    @Expose
    private List<Service> services;
    @Expose
    private String state;
    @Expose
    private String type;
    @Expose
    private String updatedAt;
    @Expose
    private String zip;
}

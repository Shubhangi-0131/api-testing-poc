
package api.stores.data;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class Service {

    @Expose
    private String createdAt;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private StoreServices storeservices;
    @Expose
    private String updatedAt;
}

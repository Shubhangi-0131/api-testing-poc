
package api.stores.data;

import com.google.gson.annotations.Expose;
import lombok.Getter;

@Getter
public class StoreServices {

    @Expose
    private String createdAt;
    @Expose
    private Long serviceId;
    @Expose
    private Long storeId;
    @Expose
    private String updatedAt;
}

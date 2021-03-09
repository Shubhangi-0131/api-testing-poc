
package api.stores.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class StoresResponse {

    @SerializedName(value = "data")
    @Expose
    private List<Store> storeList;

    @Expose
    private Long limit;
    @Expose
    private Long skip;
    @Expose
    private Long total;

}

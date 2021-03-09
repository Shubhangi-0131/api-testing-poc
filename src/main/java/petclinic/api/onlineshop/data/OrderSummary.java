package petclinic.api.onlineshop.data;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderSummary {
    @Expose
    private List<Response> cart;

    @Expose
    private double totalAmount;
}

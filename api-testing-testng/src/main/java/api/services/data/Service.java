package api.services.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Service {

    @Expose
    private Date createdAt;

    @Expose
    private String name;

    @Expose(serialize = false)
    private int id;

    @Expose
    private Date updatedAt;
}
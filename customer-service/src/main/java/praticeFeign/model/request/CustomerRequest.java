package praticeFeign.model.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CustomerRequest {
    private String name;
    private String email;
}
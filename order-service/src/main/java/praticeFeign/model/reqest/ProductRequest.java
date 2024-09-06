package praticeFeign.model.reqest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private long id;
    private String name;
    private double price;
}

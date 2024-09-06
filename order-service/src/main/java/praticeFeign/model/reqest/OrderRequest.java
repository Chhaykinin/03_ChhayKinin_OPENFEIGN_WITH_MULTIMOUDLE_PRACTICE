package praticeFeign.model.reqest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import praticeFeign.model.response.ProductResponse;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;
}

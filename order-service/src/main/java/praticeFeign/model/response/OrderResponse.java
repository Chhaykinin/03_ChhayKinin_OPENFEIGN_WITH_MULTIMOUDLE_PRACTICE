package praticeFeign.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private Long id;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productResponse;
    private LocalDate localDate;
}

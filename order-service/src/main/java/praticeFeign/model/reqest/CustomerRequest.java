package praticeFeign.model.reqest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerRequest {
    private Long id;
    private String name;
    private String email;
}

package praticeFeign.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import praticeFeign.model.response.APIResponse;
import praticeFeign.model.response.ProductResponse;
@FeignClient(name ="product-service",url = "http://localhost:8082/api/v1/product")
public interface ProductServiceFeignClient {

    @GetMapping("/{id}")
    APIResponse<ProductResponse> getProductById(@PathVariable long id);
}

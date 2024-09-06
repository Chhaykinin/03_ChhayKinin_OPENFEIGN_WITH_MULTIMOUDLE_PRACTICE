package praticeFeign.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import praticeFeign.model.response.APIResponse;
import praticeFeign.model.response.CustomerResponse;

@FeignClient(name ="customer-service",url = "http://localhost:8081/api/v1/customer")
public interface CustomerServiceFeignClient {
    @GetMapping("/{id}")
    APIResponse<CustomerResponse> getCustomerById(@PathVariable long id);
}

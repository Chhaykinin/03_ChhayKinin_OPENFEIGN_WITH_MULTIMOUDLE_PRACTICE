package praticeFeign.controller;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praticeFeign.model.Customer;
import praticeFeign.model.request.CustomerRequest;
import praticeFeign.model.response.APIResponse;
import praticeFeign.service.CustomerService;

import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private CustomerService customerService;
    @PostMapping
    @Operation(summary = "Insert customer")
    public ResponseEntity<APIResponse<Customer>> insertCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer= customerService.insertCustomer(customerRequest);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Insert customer successfully")
                .payload(customer)
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    @Operation(summary = "Get all customers")
    public ResponseEntity<APIResponse<List<Customer>>> getAllCustomer(){
        APIResponse<List<Customer>> response =APIResponse.<List<Customer>> builder()
                .message("Get all customer successfully!")
                .payload( customerService.getAllCustomer())
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get customer by id")
    public ResponseEntity<APIResponse<Customer>> getCustomerById(@PathVariable long id){
        Customer customer=customerService.getCustomerById(id);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Get customer id "+ id +" successfully!")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update customer by id")

    public ResponseEntity<APIResponse<Customer>> updateCustomerById(@PathVariable long id,@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.updateCustomerById(id,customerRequest);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Update customer id "+ id +" successfully")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by id")
    public ResponseEntity<APIResponse<Customer>> deleteCustomer(@PathVariable long id){
         customerService.deleteCustomer(id);
        APIResponse<Customer> response = APIResponse.<Customer> builder()
                .message("Delete customer id "+ id +" successfully")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

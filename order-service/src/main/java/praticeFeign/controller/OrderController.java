package praticeFeign.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praticeFeign.model.reqest.OrderRequest;
import praticeFeign.model.response.APIResponse;
import praticeFeign.model.response.OrderResponse;
import praticeFeign.service.OrderService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @Operation(summary = "Insert order")
    public ResponseEntity<APIResponse<OrderResponse>> insertOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse order = orderService.insertOrder(orderRequest);
        APIResponse<OrderResponse> response = APIResponse.<OrderResponse> builder()
                .message("Insert customer successfully")
                .payload(order)
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<APIResponse<List<OrderResponse>>> getAllOrder(){
        APIResponse<List<OrderResponse>> response =APIResponse.<List<OrderResponse>> builder()
                .message("Get all product successfully!")
                .payload( orderService.getAllOrder())
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<APIResponse<OrderResponse>> getOrderById(@PathVariable long id){
        OrderResponse orderResponse=orderService.getOrderById(id);
        APIResponse<OrderResponse> response = APIResponse.<OrderResponse> builder()
                .message("Get order id "+ id +" successfully!")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update order by id")
    public ResponseEntity<APIResponse<OrderResponse>> updateOrderById(@PathVariable long id,@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse=orderService.updateOrderById(id,orderRequest);
        APIResponse<OrderResponse> response = APIResponse.<OrderResponse> builder()
                .message("Update order id "+ id +" successfully!")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by id")
    public ResponseEntity<APIResponse<OrderResponse>> deleteOrderById(@PathVariable long id){
        orderService.deleteOrderById(id);
        APIResponse<OrderResponse> response = APIResponse.<OrderResponse> builder()
                .message("Delete order id "+ id +" successfully")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

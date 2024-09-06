package praticeFeign.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import praticeFeign.model.Product;
import praticeFeign.model.request.ProductRequest;
import praticeFeign.model.response.APIResponse;
import praticeFeign.service.ProductService;
import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @Operation(summary = "Insert Product")
    public ResponseEntity<APIResponse<Product>> insertProduct(@RequestBody ProductRequest productRequest){
        Product product=productService.insertProduct(productRequest);
        APIResponse<Product> response = APIResponse.<Product> builder()
                .message("Insert product successfully")
                .payload(product)
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    @Operation(summary = "Get all Products")
    public ResponseEntity<APIResponse<List<Product>>> getAllProduct(){
        APIResponse<List<Product>> response =APIResponse.<List<Product>> builder()
                .message("Get all product successfully!")
                .payload( productService.getAllProduct())
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable long id){
        Product customer=productService.getProductById(id);
        APIResponse<Product> response = APIResponse.<Product> builder()
                .message("Get product id "+ id +" successfully!")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update product by id")
    public ResponseEntity<APIResponse<Product>> updateProductById(@PathVariable long id,@RequestBody ProductRequest productRequest){
        Product customer = productService.updateProductById(id,productRequest);
        APIResponse<Product> response = APIResponse.<Product> builder()
                .message("Update product id "+ id +" successfully")
                .payload(customer)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id")
    public ResponseEntity<APIResponse<Product>> deleteProductById(@PathVariable long id){
        productService.deleteProductById(id);
        APIResponse<Product> response = APIResponse.<Product> builder()
                .message("Delete product id "+ id +" successfully")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

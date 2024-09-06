package praticeFeign.service;

import praticeFeign.model.Product;
import praticeFeign.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    Product insertProduct(ProductRequest productRequest);

    List<Product> getAllProduct();

    Product getProductById(long id);

    Product updateProductById(long id, ProductRequest productRequest);

    void deleteProductById(long id);
}

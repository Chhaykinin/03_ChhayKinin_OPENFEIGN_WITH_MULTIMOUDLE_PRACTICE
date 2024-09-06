package praticeFeign.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import praticeFeign.model.Product;
import praticeFeign.model.request.ProductRequest;
import praticeFeign.repository.ProductRepository;
import praticeFeign.service.ProductService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product insertProduct(ProductRequest productRequest) {
        Product product =new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product updateProductById(long id, ProductRequest productRequest) {
        Product product=productRepository.findById(id).orElseThrow();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}

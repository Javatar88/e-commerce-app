package com.ecommerce.product.mapper;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.model.ProductPurchaseResponse;
import com.ecommerce.product.model.ProductRequest;
import com.ecommerce.product.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
       return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder().id(productRequest.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return  new
                ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchased(Product product, double quantity) {
        return  new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
                );
    }
}

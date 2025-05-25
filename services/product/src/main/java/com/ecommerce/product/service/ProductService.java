package com.ecommerce.product.service;

import com.ecommerce.product.Exception.ProductPurchaseException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.ProductPurchaseRequest;
import com.ecommerce.product.model.ProductPurchaseResponse;
import com.ecommerce.product.model.ProductRequest;
import com.ecommerce.product.model.ProductResponse;
import com.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct( ProductRequest productRequest) {
        var product=mapper.toProduct(productRequest);

        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds= request
                .stream()
                .map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("one or more product does not exist");
        }
       var storedRequest=request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
       var purchasedProducts= new ArrayList<ProductPurchaseResponse>();
       for (int i=0;i < storedProducts.size() ;i++){
         var product=storedProducts.get(i);
         var productRequest= storedRequest.get(i);
         if(product.getAvailableQuantity() < productRequest.quantity()){
             throw new ProductPurchaseException("insufficient stock quantity for product" +productRequest.productId());
         }
         var newAvailableQuantity =product.getAvailableQuantity()- productRequest.quantity();
         product.setAvailableQuantity(newAvailableQuantity);
         productRepository.save(product);
         purchasedProducts.add(mapper.toProductPurchased(product,productRequest.quantity()));

       }

       return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product Not Found"));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}

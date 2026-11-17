package com.scaler.productservice.service;

import com.scaler.productservice.clients.FakeStoreApiClient;
import com.scaler.productservice.dto.CategoryDto;
import com.scaler.productservice.dto.FakeStoreProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductDetailsById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(id);
        if (fakeStoreProductDto != null) {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public void deleteProductById(Long id) {
        requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", null, null, id);
    }

    @Override
    public Product createProduct(Product inputProduct) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(inputProduct);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.POST, "https://fakestoreapi.com/products", fakeStoreProductDtoInput, FakeStoreProductDto.class, null);

        if (fakeStoreProductDtoResponseEntity.hasBody() && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

            Product product = from(fakeStoreProductDto);
            return product;
        }
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product inputProduct) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(inputProduct);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", fakeStoreProductDtoInput, FakeStoreProductDto.class, id);

        if (fakeStoreProductDtoResponseEntity.hasBody() && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

            Product product = from(fakeStoreProductDto);
            return product;
        }
        return null;
    }

    // REQUEST FOR ENTITY

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor =
                restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback,
                responseExtractor, uriVariables);
    }

    // INFO() MAPPERS

    // 1. MAP FakeStoreProductDto to Product
    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    // 2. MAP Product to FakeStoreProductDto
    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}

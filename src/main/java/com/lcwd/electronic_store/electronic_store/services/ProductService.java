package com.lcwd.electronic_store.electronic_store.services;

import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.ProductsDto;
import com.lcwd.electronic_store.electronic_store.dto.UserDto;

import java.util.List;

public interface ProductService {
    ProductsDto createProduct(ProductsDto productsDto);
    PageResponse<ProductsDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);
}

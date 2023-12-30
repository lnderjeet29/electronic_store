package com.lcwd.electronic_store.electronic_store.services.impl;

import com.lcwd.electronic_store.electronic_store.config.ProjectConfig;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.ProductsDto;
import com.lcwd.electronic_store.electronic_store.entity.Products;
import com.lcwd.electronic_store.electronic_store.helper.PageHelper;
import com.lcwd.electronic_store.electronic_store.repository.ProductsRepo;
import com.lcwd.electronic_store.electronic_store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ProductImp implements ProductService {
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private ProjectConfig projectConfig;
    @Override
    public ProductsDto createProduct(ProductsDto productsDto) {
        String id= UUID.randomUUID().toString();
        productsDto.setProductId(id);
        Products products=projectConfig.modelMapper().map(productsDto,Products.class);
        productsRepo.save(products);
        return productsDto;
    }

    @Override
    public PageResponse<ProductsDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Products> page=productsRepo.findAll(pageable);
        PageResponse<ProductsDto> pageResponse= PageHelper.getPageResponse(page,ProductsDto.class);
        return pageResponse;
    }
}

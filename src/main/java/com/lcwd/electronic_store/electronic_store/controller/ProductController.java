package com.lcwd.electronic_store.electronic_store.controller;

import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.dto.ProductsDto;
import com.lcwd.electronic_store.electronic_store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<ProductsDto> createProduct(@RequestBody ProductsDto productsDto){
        ProductsDto productsDto1=productService.createProduct(productsDto);
        return new ResponseEntity<>(productsDto1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<PageResponse<ProductsDto>> getAllProduct(
      @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
      @RequestParam(value = "pageSize",defaultValue = "4",required = false) int pageSize,
      @RequestParam(value = "sortBy",defaultValue = "productName",required = false) String sortBy,
      @RequestParam(value = "sortDir",defaultValue = "desc",required = false) String sortDir
    ){
        PageResponse<ProductsDto> pageResponse=productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(pageResponse,HttpStatus.OK);
    }
}

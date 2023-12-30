package com.lcwd.electronic_store.electronic_store.services;

import com.lcwd.electronic_store.electronic_store.dto.CateogoryDto;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;

public interface CategoryService {
    CateogoryDto createCategory(CateogoryDto cateogoryDto);
    CateogoryDto getCategory(String id);
    PageResponse<CateogoryDto> getAllCategory(int pageNumber,int pageSize,String sortBy,String sortDir);
    CateogoryDto UpdateCategory(String id,CateogoryDto cateogoryDto);
    String deleteCategory(String id);
}

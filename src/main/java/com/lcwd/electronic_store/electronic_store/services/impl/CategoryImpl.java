package com.lcwd.electronic_store.electronic_store.services.impl;

import com.lcwd.electronic_store.electronic_store.config.ProjectConfig;
import com.lcwd.electronic_store.electronic_store.dto.CateogoryDto;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.entity.Category;
import com.lcwd.electronic_store.electronic_store.exception.DataNotFoundException;
import com.lcwd.electronic_store.electronic_store.helper.PageHelper;
import com.lcwd.electronic_store.electronic_store.repository.CategoryRepo;
import com.lcwd.electronic_store.electronic_store.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProjectConfig projectConfig;
    Logger logger= LoggerFactory.getLogger(CategoryImpl.class);
    @Override
    public CateogoryDto createCategory(CateogoryDto cateogoryDto) {
        String id= UUID.randomUUID().toString();
        cateogoryDto.setCategoryId(id);
        Category category = categoryDtoToCategory(cateogoryDto);
        categoryRepo.save(category);
        return cateogoryDto;
    }

    @Override
    public CateogoryDto getCategory(String id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("id data not found in database..."));
        return categoryToDto(category);
    }

    @Override
    public PageResponse<CateogoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepo.findAll(pageable);
        PageResponse<CateogoryDto> pageResponse = PageHelper.getPageResponse(page, CateogoryDto.class);
        return pageResponse;
    }

    @Override
    public CateogoryDto UpdateCategory(String id, CateogoryDto cateogoryDto) {
        logger.info("update categorgy method run");
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("id data not found..."));
        category.setCategoryId(category.categoryId);
        category.setCategoryName(cateogoryDto.categoryName);
        category.setCategoryDesc(category.categoryDesc);
        category.setCoverImage(cateogoryDto.coverImage);
        categoryRepo.save(category);
        logger.info("update data succesfully...");
        return categoryToDto(category);
    }

    @Override
    public String deleteCategory(String id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new DataNotFoundException("id data not found..."));
        categoryRepo.delete(category);
        return "Deleted successfully";
    }


    public CateogoryDto categoryToDto(Category category) {
        return projectConfig.modelMapper().map(category, CateogoryDto.class);
    }

    public Category categoryDtoToCategory(CateogoryDto cateogoryDto) {
        return projectConfig.modelMapper().map(cateogoryDto, Category.class);
    }
}

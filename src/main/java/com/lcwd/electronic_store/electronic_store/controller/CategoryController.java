package com.lcwd.electronic_store.electronic_store.controller;

import com.lcwd.electronic_store.electronic_store.dto.CateogoryDto;
import com.lcwd.electronic_store.electronic_store.dto.ImageResponse;
import com.lcwd.electronic_store.electronic_store.dto.PageResponse;
import com.lcwd.electronic_store.electronic_store.services.CategoryService;
import com.lcwd.electronic_store.electronic_store.services.FileServiceCategory;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileServiceCategory fileServiceCategory;
    @Value("${img.profile.upload}")
    private String imagePath;
    @PostMapping("/create")
    public ResponseEntity<CateogoryDto> createCategory(@Valid @RequestBody CateogoryDto cateogoryDto) {
        CateogoryDto category= categoryService.createCategory(cateogoryDto);
        return new ResponseEntity<>(category,HttpStatus.ACCEPTED);
    }

   @GetMapping("/getCategory/{id}")
    public ResponseEntity<CateogoryDto> getCategory(@PathVariable("id") String id) {
        CateogoryDto category=categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<PageResponse<CateogoryDto>> getAllCategory
            (@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
             @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
             @RequestParam(value = "sortBy",defaultValue = "categoryName",required = false) String sortBy,
             @RequestParam(value = "sortDir",defaultValue = "asce",required = false) String sortDir) {
        PageResponse<CateogoryDto> category=categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CateogoryDto> UpdateCategory(@PathVariable("id") String id,@RequestBody CateogoryDto cateogoryDto) {
        CateogoryDto category=categoryService.UpdateCategory(id,cateogoryDto);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String id) {
        String s=categoryService.deleteCategory(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("file")MultipartFile file,@PathVariable("id") String id) throws IOException {
        String imageName=fileServiceCategory.fileUpload(file,imagePath);
        System.out.println(imageName);
        CateogoryDto cateogoryDto=categoryService.getCategory(id);
        cateogoryDto.setCoverImage(imageName);
        categoryService.UpdateCategory(id,cateogoryDto);
        ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).message("image uploaded...").status(true).httpStatus(HttpStatus.ACCEPTED).build();
        return new ResponseEntity<>(imageResponse,HttpStatus.ACCEPTED);

    }
    @GetMapping("getImageById/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        CateogoryDto cateogoryDto=categoryService.getCategory(id);
        System.out.println(imagePath+cateogoryDto.coverImage);
        InputStream resource=fileServiceCategory.getFileResource(imagePath,cateogoryDto.coverImage);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}

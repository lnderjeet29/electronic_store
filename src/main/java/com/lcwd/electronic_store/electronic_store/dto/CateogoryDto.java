package com.lcwd.electronic_store.electronic_store.dto;


import com.lcwd.electronic_store.electronic_store.validator.ImageNameValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CateogoryDto {
    public String categoryId;
    @Size(min = 4,max = 20,message = "please enter the valid name")
    @NotBlank(message = "please enter the name...")
    public String categoryName;
    @NotBlank(message = "desc shouldn't be blank...")
    public String categoryDesc;
//    @ImageNameValid(message = "image is not valid...")
    public String coverImage;
}

package com.lcwd.electronic_store.electronic_store.dto;

import com.lcwd.electronic_store.electronic_store.validator.ImageNameValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    public int userId;
    @Size(max = 20,min = 2,message = "please enter the valid name!!")
    public String userName;
    @Size(max = 8,min = 2,message = "invalid password...")
    public String password;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "invalid email...")
    @NotBlank(message = "please enter the email...")
    public String email;
    @ImageNameValid
    public String image_name;
}

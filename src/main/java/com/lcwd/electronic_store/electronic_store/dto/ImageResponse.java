package com.lcwd.electronic_store.electronic_store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private String imageName;
    private String message;
    private boolean status;
    private HttpStatus httpStatus;
}

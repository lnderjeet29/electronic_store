package com.lcwd.electronic_store.electronic_store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiRepositoryMessage {
    private String message;
    private boolean status;
    private HttpStatus httpStatus;
}

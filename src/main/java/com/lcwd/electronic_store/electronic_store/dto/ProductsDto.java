package com.lcwd.electronic_store.electronic_store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductsDto {
    private String productId;
    private String productName;
    private String productDesc;
    private int prize;
    private boolean isLive;
    private boolean stock;
}

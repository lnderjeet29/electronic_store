package com.lcwd.electronic_store.electronic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Products")
public class Products {

    @Id
    private String productId;
    private String productName;
    @Column(length = 10000)
    private String productDesc;
    private int prize;
    private boolean isLive;
    private boolean stock;
}

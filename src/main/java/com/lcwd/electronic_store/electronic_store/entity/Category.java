package com.lcwd.electronic_store.electronic_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    public String categoryId;
    @Column(name = "category_name", length = 20,nullable = false)
    public String categoryName;
    @Column(name = "category_desc",length = 100)
    public String categoryDesc;
    @Column(name = "cover_image")
    public String coverImage;
}

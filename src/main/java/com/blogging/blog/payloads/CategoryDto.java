package com.blogging.blog.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;
    @NotEmpty
    @Size(message = "Title must have minimum 4 characters",min=4)
    private String categoryTitle;
    @NotEmpty(message = "Category Description must not be empty")
    private String categoryDescription;
}

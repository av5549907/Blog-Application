package com.blogging.blog.payloads;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    @NotEmpty
    private String postTitle;
    @NotEmpty
    private  String postContent;
    private  String imageName;
    private Date addedDate;
    @ManyToOne
    private  User user;
    @ManyToOne
    private Category category;
}

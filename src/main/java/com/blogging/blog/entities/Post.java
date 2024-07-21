package com.blogging.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(name="post_title", length = 100)
    private String postTitle;
    @Column(length = 100000)
    private  String postContent;
    private  String imageName;
    private Date addedDate;
    @ManyToOne
    private  User user;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;


}

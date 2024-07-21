package com.blogging.blog.repository;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.Post;
import com.blogging.blog.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);

    List<Post> findByPostTitleContaining(String postTitle);
    @Query("select p from Post p where p.postTitle like :key")
    List<Post> findByTitle(@Param("key")String postTitle);

}

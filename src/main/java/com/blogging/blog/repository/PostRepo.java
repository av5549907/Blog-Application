package com.blogging.blog.repository;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.Post;
import com.blogging.blog.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByCategory(Category category);
}

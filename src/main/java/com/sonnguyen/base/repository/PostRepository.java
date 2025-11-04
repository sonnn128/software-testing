package com.sonnguyen.base.repository;

import com.sonnguyen.base.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}

package com.ip.posts.repo;

import org.springframework.data.repository.CrudRepository;

import com.ip.posts.domain.Post;

/**
 * Posts repo.
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
}

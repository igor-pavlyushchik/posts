package com.ip.posts.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ip.posts.domain.Post;
import com.ip.posts.repo.PostRepository;


/**
 * Posts Controller.
 *
 */
@RestController
public class PostController {

    private PostRepository postRepository;
    private RestTemplate restTemplate;
    @Value("${user.url}")
    private String userUrl;


    @Autowired
    public PostController(final PostRepository postRepository, final RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/greeting")
    public String hello(@RequestParam(value = "name", defaultValue = "Posts service") String name) {
        return String.format("Hello, k8s and %s!", name);
    }

    @PostMapping("/posts")
    @Transactional
    public Post create(@RequestBody final Post post) {
        Post savedPost = postRepository.save(post);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(userUrl, HttpMethod.PUT, null, Void.class, post.getAuthorId(), 1 );
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            return savedPost;
        }
        throw new RuntimeException("Some issue in microservices communication. " +
                "Response: responseEntity.getStatusCode()" + responseEntity.getStatusCode());
    }

    @GetMapping("/posts/{id}")
    public Post postData(@PathVariable(value = "id") final Integer id) {
        return postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such post exists."));
    }

    @DeleteMapping("/posts/{id}")
    public void deleteById(@PathVariable(value = "id") final Integer id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such post exists."));
        postRepository.deleteById(id);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(userUrl, HttpMethod.PUT, null, Void.class, existingPost.getAuthorId(), -1 );
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Some issue in microservices communication. " +
                    "Response: responseEntity.getStatusCode()" + responseEntity.getStatusCode());
        }
    }

    @PutMapping("/posts/{id}")
    @Transactional
    public Post update(@PathVariable(value = "id") Integer id,  @RequestBody final Post post) {
        Post existingPost = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No such post exists."));
            existingPost.setText(post.getText());
            return postRepository.save(existingPost);
    }
}

package com.springboot.blog.blogrestapi.controller;

import com.springboot.blog.blogrestapi.payload.PostDto;
import com.springboot.blog.blogrestapi.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDto> cretae_post(@RequestBody PostDto  postDto ){
        return new ResponseEntity<>(postService.create_post(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> get_all_post( ){
        return postService.get_all_posts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostId( @PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostId(id));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto>  updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        PostDto postResponse= postService.updatePost(postDto, id);
        return new ResponseEntity<>((postResponse), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePostId(id);
        String message = "Post entity deleted successfully";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\": \"" + message + "\"}");
    }
}

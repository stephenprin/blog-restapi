package com.springboot.blog.blogrestapi.service;

import com.springboot.blog.blogrestapi.entity.Post;
import com.springboot.blog.blogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.blogrestapi.payload.PostDto;
import com.springboot.blog.blogrestapi.repository.PostRepository;
import com.springboot.blog.blogrestapi.service.impl.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PostDto create_post(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post newPost= postRepository.save(post);

        PostDto postResponse= mapToDto(newPost);

        return postResponse;
    }

    @Override
    public PostDto getPostId(long id) {
       Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
       return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDesription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost= postRepository.save(post);
        return mapToDto(updatePost);
    }

    @Override
    public void deletePostId(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


    @Override
    public List<PostDto> get_all_posts() {
        List<Post>  posts= postRepository.findAll();
        return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }


    //mapping  to DTO
    private PostDto  mapToDto(Post  post){
        PostDto postDto=  new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDesription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // mapping tooo Entity
    private Post mapToEntity(PostDto postDto){
        Post post=  new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDesription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}

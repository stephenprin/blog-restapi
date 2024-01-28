package com.springboot.blog.blogrestapi.service.impl
        ;

import com.springboot.blog.blogrestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> get_all_posts();

    PostDto create_post(PostDto postDto);
    PostDto  getPostId(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostId(long id);
}

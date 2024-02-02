package com.springboot.blog.blogrestapi.service;

import com.springboot.blog.blogrestapi.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

package com.springboot.blog.blogrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String  title;
    @Column(name = "description", nullable = false)
    private String desription;
    @Column(name = "content", nullable = false)
    private String content;
}

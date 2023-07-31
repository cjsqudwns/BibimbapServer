package com.example.BibimbapServer.web.dto;

import com.example.BibimbapServer.Domain.like.Likes;
import com.example.BibimbapServer.Domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
    private int likesCount;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.likesCount = entity.getLikes().size();
    }
}
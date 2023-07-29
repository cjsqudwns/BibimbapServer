package com.example.BibimbapServer.web.dto;

import com.example.BibimbapServer.Domain.like.Likes;
import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private Long id;
    private Posts post;
    private Member member;
    private LocalDateTime like_date;

    @Builder
    public LikeResponseDto(Likes entity){
        this.id = entity.getId();
        this.post = entity.getPost();
        this.member = entity.getMember();
        this.like_date = entity.getCreatedDate();
    }
}

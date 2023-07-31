package com.example.BibimbapServer.Domain.like;

import com.example.BibimbapServer.Domain.BaseTimeEntity;
import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.posts.Posts;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Likes extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Likes(Posts posts, Member member){
        this.posts = posts;
        this.member = member;
    }
}

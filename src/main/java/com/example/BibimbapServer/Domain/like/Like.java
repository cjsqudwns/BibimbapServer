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
public class Like extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Like(Posts post, Member member){
        this.post = post;
        this.member = member;
    }
}

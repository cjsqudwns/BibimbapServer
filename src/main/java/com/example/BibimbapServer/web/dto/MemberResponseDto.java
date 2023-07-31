package com.example.BibimbapServer.web.dto;

import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.member.Role;
import com.example.BibimbapServer.Domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    @Builder
    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
    }
}

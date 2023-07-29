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
    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
    }
}

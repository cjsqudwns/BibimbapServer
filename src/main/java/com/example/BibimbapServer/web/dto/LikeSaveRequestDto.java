package com.example.BibimbapServer.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeSaveRequestDto {
    private Long postId;
    private Long memberId;
    @Builder
    public LikeSaveRequestDto(Long postId, Long memberId){
        this.postId = postId;
        this.memberId = memberId;
    }
}

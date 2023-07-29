package com.example.BibimbapServer.web;

import com.example.BibimbapServer.service.likes.LikesService;
import com.example.BibimbapServer.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/like/{memberId}/{postId}")
    public long save(@PathVariable Long memberId, @PathVariable Long postId) { // @RequestBody LikeSaveRequestDto requestDto
        System.out.println(memberId);
        System.out.println(postId);
        return likesService.save(memberId, postId);
    }
    @DeleteMapping("/like/{memberId}/{postId}")
    @ResponseBody // 추가 후 delete error 해결
    public Long delete(@PathVariable Long memberId, @PathVariable Long postId) {
        return likesService.delete(memberId, postId);
    }

    /*@GetMapping("/like/{postId}")
    public int getLikeCount(@PathVariable Long postId){
        return likesService.getLikeCount(postId);
    }*/

    @GetMapping("/like/member/{memberId}/{postId}/{likeId}")
    public MemberResponseDto findMemberById(@PathVariable Long memberId, @PathVariable Long postId, @PathVariable Long likeId) {
        return likesService.findMemberById(likeId);
    }
    @GetMapping("/api/v1/posts/{postId}/like")
    public List<MemberResponseDto> findAllMember(@PathVariable Long postId) {
        return likesService.findAllMember(postId);
    }
}

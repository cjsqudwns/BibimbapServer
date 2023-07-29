package com.example.BibimbapServer.web;

import com.example.BibimbapServer.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like/{memberId}/{postId}")
    public long save(@PathVariable Long memberId, @PathVariable Long postId) { // @RequestBody LikeSaveRequestDto requestDto
        System.out.println(memberId);
        System.out.println(postId);
        return likeService.save(memberId, postId);
    }
    @DeleteMapping("/like/{memberId}/{postId}")
    @ResponseBody // 추가 후 delete error 해결
    public Long delete(@PathVariable Long memberId, @PathVariable Long postId) {
        return likeService.delete(memberId, postId);
    }

    @GetMapping("/like/{postId}")
    public int getLikeCount(@PathVariable Long postId){
        return likeService.getLikeCount(postId);
    }

//    @GetMapping("/api/v1/posts/{id}")
//    public LikeResponseDto findById(@PathVariable Long id) {
//        return likeService.findById(id);
//    }
}

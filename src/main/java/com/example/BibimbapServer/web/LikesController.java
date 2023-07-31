package com.example.BibimbapServer.web;

import com.example.BibimbapServer.service.likes.LikesService;
import com.example.BibimbapServer.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/{postsId}/{memberId}")
    public long save(@PathVariable Long postsId, @PathVariable Long memberId) { // @RequestBody LikeSaveRequestDto requestDto
        return likesService.save(postsId, memberId);
    }
    @DeleteMapping("/{postsId}/{memberId}")
    @ResponseBody // 추가 후 delete error 해결
    public Long delete(@PathVariable Long postsId, @PathVariable Long memberId) {
        return likesService.delete(postsId, memberId);
    }

    @GetMapping("/{likeId}")
    public MemberResponseDto findMember(@PathVariable Long likeId) {
        return likesService.findMemberByLikeId(likeId);
    }
    @GetMapping("/list/{postsId}")
    public List<MemberResponseDto> findAllMember(@PathVariable Long postsId) {
        return likesService.findAllMemberByPostsId(postsId);
    }
    @GetMapping("/{postsId}/likesCount")
    public int getLikesCount(@PathVariable Long postsId){
        return likesService.getLikesCount(postsId);
    }
}

package com.example.BibimbapServer.service.like;

import com.example.BibimbapServer.Domain.like.Likes;
import com.example.BibimbapServer.Domain.like.LikeRepository;
import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.member.MemberRepository;
import com.example.BibimbapServer.Domain.posts.Posts;
import com.example.BibimbapServer.Domain.posts.PostsRepository;
import com.example.BibimbapServer.web.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(Long memberId, Long postId) { // LikeSaveRequestDto requestDto
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + memberId));
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 post가 없습니다. id=" + postId));
        post.setLikeCount(post.getLikeCount() + 1);
//        Posts posts = postsRepository.findById(requestDto.getPostId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 post가 없습니다. id=" + requestDto.getPostId()));
//        Member member = memberRepository.findById(requestDto.getMemberId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + requestDto.getMemberId()));
        return likeRepository.save(Likes.of(post, member)).getId();
    }

    @Transactional
    public Long delete (Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + memberId));
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 post가 없습니다. id=" + postId));
        Like
        return likeRepository.delete(post);
    }
    @Transactional
    public int getLikeCount(Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        return posts.getLikeCount();
    }

    public LikeResponseDto findById(Long likeId) {
        Likes entity = likeRepository.findById(likeId).orElseThrow(() -> new IllegalArgumentException("해당 좋아요 정보가 없습니다. id=" + likeId));
        return new LikeResponseDto(entity);
    }

//        public LikeResponseDto findAll(Long likeId){
//
//        }
}


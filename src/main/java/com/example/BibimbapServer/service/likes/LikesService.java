package com.example.BibimbapServer.service.likes;

import com.example.BibimbapServer.Domain.like.Likes;
import com.example.BibimbapServer.Domain.like.LikesRepository;
import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.member.MemberRepository;
import com.example.BibimbapServer.Domain.posts.Posts;
import com.example.BibimbapServer.Domain.posts.PostsRepository;
import com.example.BibimbapServer.web.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
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
        return likesRepository.save(Likes.of(post, member)).getId();
    }

    @Transactional
    public Long delete (Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 member가 없습니다. id=" + memberId));
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 post가 없습니다. id=" + postId));
        post.setLikeCount(post.getLikeCount() - 1);
        Likes like = likesRepository.findByPostAndMember(post, member).get(0);
        Long likeId = like.getId();
        likesRepository.delete(like);
        return likeId;
    }
    @Transactional(readOnly = true)
    public int getLikeCount(Long postId) {
        Posts posts = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
        return posts.getLikeCount();
    }
    @Transactional(readOnly = true)
    public MemberResponseDto findMemberById(Long likeId) {
        Likes like = likesRepository.findById(likeId).orElseThrow(() -> new IllegalArgumentException("해당 좋아요 정보가 없습니다. id=" + likeId));
        Member member = like.getMember();
        return new MemberResponseDto(member);
    }
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember(Long postId) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 post가 없습니다. id=" + postId));
        List<Likes> likesList = likesRepository.findAllByPost(post);
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for(int i = 0; i < likesList.size(); i++){
            MemberResponseDto temp = new MemberResponseDto(likesList.get(i).getMember());
            memberResponseDtoList.add(temp);
        }
        return memberResponseDtoList;
    }

}


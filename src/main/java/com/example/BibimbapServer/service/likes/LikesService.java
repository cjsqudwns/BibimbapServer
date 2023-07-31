package com.example.BibimbapServer.service.likes;

import com.example.BibimbapServer.Domain.like.Likes;
import com.example.BibimbapServer.Domain.like.LikesRepository;
import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.member.MemberRepository;
import com.example.BibimbapServer.Domain.posts.Posts;
import com.example.BibimbapServer.Domain.posts.PostsRepository;
import com.example.BibimbapServer.exception.LikeToggleException;
import com.example.BibimbapServer.exception.MemberNotExistException;
import com.example.BibimbapServer.exception.PostsNotExistException;
import com.example.BibimbapServer.web.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;
    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(Long postsId, Long memberId) { // LikeSaveRequestDto requestDto
        Optional<Likes> byMemberIdAndPostId = likesRepository.findByMemberIdAndPostsId(memberId, postsId);
        if(byMemberIdAndPostId.isPresent())
            throw new LikeToggleException("좋아요가 눌려있습니다.");
        Posts post = postsRepository.findById(postsId)
                .orElseThrow(() -> new PostsNotExistException("해당 id의 post가 없습니다 : " + postsId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotExistException("해당 id의 member가 없습니다 : " + memberId));
        Likes likeEntity = Likes.builder()
                .posts(post)
                .member(member)
                .build();
        return likesRepository.save(likeEntity).getId();
    }

    @Transactional
    public Long delete (Long postsId, Long memberId) {
        Optional<Likes> byMemberIdAndPostId = likesRepository.findByMemberIdAndPostsId(memberId, postsId);
        if(!byMemberIdAndPostId.isPresent())
            throw new LikeToggleException("좋아요가 안눌려있습니다.");
        Long deleteId = byMemberIdAndPostId.get().getId();
        likesRepository.deleteById(deleteId);
        return deleteId;
    }
    @Transactional(readOnly = true)
    public MemberResponseDto findMemberByLikeId(Long likeId) {
        Likes like = likesRepository.findById(likeId)
                .orElseThrow(() -> new LikeToggleException("해당 id의 like가 없습니다 : " + likeId));
        return MemberResponseDto.builder()
                .member(like.getMember())
                .build();
    }
    @Transactional(readOnly = true)
    public int getLikesCount(Long postsId){
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 posts가 없습니다 : " + postsId));
        return posts.getLikes().size();
    }
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMemberByPostsId(Long postsId) {
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 posts가 없습니다 : " + postsId));

        return posts.getLikes().stream()
                .map(entity -> {
                    return MemberResponseDto.builder()
                            .member(entity.getMember())
                            .build();
                })
                .collect(Collectors.toList());
    }

}


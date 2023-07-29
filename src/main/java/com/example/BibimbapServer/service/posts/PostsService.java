package com.example.BibimbapServer.service.posts;

import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.member.MemberRepository;
import com.example.BibimbapServer.Domain.posts.Posts;
import com.example.BibimbapServer.Domain.posts.PostsRepository;
import com.example.BibimbapServer.security.dto.SessionUser;
import com.example.BibimbapServer.web.dto.PostsListResponseDto;
import com.example.BibimbapServer.web.dto.PostsResponseDto;
import com.example.BibimbapServer.web.dto.PostsSaveRequestDto;
import com.example.BibimbapServer.web.dto.PostsUpdateRequestDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.persistence.annotations.ReadOnly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        Member member = memberRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 email을 가진 member가 없습니다. id=" + user.getEmail()));
        posts.setMember(member);
        return postsRepository.save(posts).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    public PostsResponseDto findByEmail(Long id, String email){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        Member sessionMember = entity.getMember();
        Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        if(findMember == sessionMember){
            return new PostsResponseDto(entity);
        }else{
            return null;
        }

    }
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
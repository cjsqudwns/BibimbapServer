package com.example.BibimbapServer.Domain.like;

import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByPostsAndMember(Posts posts, Member member);
    Optional<Likes> findByMemberIdAndPostsId(Long memberId, Long postsId);
    List<Likes> findAllByPosts(Posts posts);
}

package com.example.BibimbapServer.Domain.like;

import com.example.BibimbapServer.Domain.member.Member;
import com.example.BibimbapServer.Domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByPostAndMember(Posts post, Member member);
    List<Likes> findAllByPost(Posts post);
}

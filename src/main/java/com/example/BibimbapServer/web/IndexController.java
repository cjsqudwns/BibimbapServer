package com.example.BibimbapServer.web;

import com.example.BibimbapServer.security.dto.SessionUser;
import com.example.BibimbapServer.service.posts.PostsService;
import com.example.BibimbapServer.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    // "/"로 GET 요청이 들어오면 index.mustache를 띄워줌
    @GetMapping("/")
    public String index(Model model) { //추가된 부분
        model.addAttribute("posts", postsService.findAllDesc());  //추가된 부분
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    // "/posts/save"로 GET 요청이 들어오면 posts-save.mustache를 띄워줌
    @GetMapping("/posts/save")
    public String postsSave(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        try{
            if(!dto.getMember().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException();
        }catch(Exception e){
            System.out.println("글 작성자 본인만 글을 수정할 수 있습니다");
            return "/";
        }

        model.addAttribute("post", dto);
        return "posts-update";
    }
    @DeleteMapping("/api/v1/posts/{id}")
    @ResponseBody // 추가 후 delete error 해결
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
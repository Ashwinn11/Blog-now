package com.project.blognow.controller;
import  com.project.blognow.model.Post;
import com.project.blognow.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class HomeController {
    @Autowired
    private PostService postService;
    @GetMapping("/posts")
    public String fetchPosts(Model model){
        List<Post> posts = postService.getAllPost();
         model.addAttribute("posts",posts);
         return "home";
    }
}

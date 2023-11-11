package com.project.blognow.controller;

import com.project.blognow.model.Account;
import com.project.blognow.model.Post;
import com.project.blognow.repository.PostRepository;
import com.project.blognow.service.AccountService;
import com.project.blognow.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PostRepository postRepository;
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> posts = postService.getById(id);
        if(posts.isPresent()){
             Post post = posts.get();
             model.addAttribute("post",post);
             return "post";
        }
        return "404";
    }
    @GetMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal){
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_new";
        }else {
            return "404";
        }
    }
    @PostMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String saveNewPost(@ModelAttribute Post post,Principal principal){
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (post.getAccount().getEmail().compareToIgnoreCase(authUsername) < 0) {
        }
        postService.savePost(post);
        return "redirect:/posts/"+ post.getId();
    }
    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String editPost(@PathVariable Long id , Model model){
        Optional<Post> posts = postService.getById(id);
        if(posts.isPresent()){
            Post post = posts.get();
            model.addAttribute("post",post);
            return "post_edit";
        }
        else return "404";
    }
    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id , Post post, BindingResult result , Model model) {
        Optional<Post> posts = postService.getById(id);
        if (posts.isPresent()) {
            Post existing = posts.get();
            existing.setTitle(post.getTitle());
            existing.setBody(post.getBody());
           existing.setModifiedAt(LocalDateTime.now());
            postService.savePost(existing);
        }
        return "redirect:/posts/"+post.getId();
    }
    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id ,Model model){
        Optional<Post> posts = postService.getById(id);
        if(posts.isPresent()){
            postRepository.delete(posts.get());
            return "redirect:/posts";
        }
        else return "404";
    }

}

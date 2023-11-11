package com.project.blognow.config;

import com.project.blognow.model.Account;
import com.project.blognow.model.Authority;
import com.project.blognow.model.Post;
import com.project.blognow.repository.AuthorityRepository;
import com.project.blognow.service.AccountService;
import com.project.blognow.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;
    @Autowired
    private AccountService  accountService;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAllPost();
        Authority user = new Authority();
        user.setName("ROLE_USER");
        authorityRepository.save(user);

        Authority admin = new Authority();
        admin.setName("ROLE_ADMIN");
        authorityRepository.save(admin);

        if (posts.isEmpty()) {
            Account account1 = new Account();

            account1.setFirstName("Ashwin");
            account1.setLastName("Anbazhagan");
            account1.setEmail("ashwinnanbazhagan@gmail.com");
            account1.setPassword("Ashwiin@11");
            Set<Authority> authoritySet = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authoritySet::add);
            account1.setAuthorities(authoritySet);

            Account account2 = new Account();

            account2.setFirstName("Solitary");
            account2.setLastName("Soul");
            account2.setEmail("solitaryysoul@gmail.com");
            account2.setPassword("Ashwiin@1430");
            Set<Authority> authoritySet1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authoritySet1::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authoritySet1::add);
            account2.setAuthorities(authoritySet1);

            accountService.save(account1);
            accountService.save(account2);

            Post post = new Post();
            post.setTitle("Spring Blog");
            post.setBody("This is a blogging project which lets user to post their views");
            post.setAccount(account1);

            Post post1 = new Post();
            post1.setTitle("Welcome to the bloogy");
            post1.setBody("This is the welcome post by the ADMIN");
            post1.setAccount(account2);

            postService.savePost(post1);
            postService.savePost(post);
        }
    }
}
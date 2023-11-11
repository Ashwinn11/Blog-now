package com.project.blognow.service;

import com.project.blognow.model.Post;
import com.project.blognow.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getById(long id){
        return postRepository.findById(id);
    }
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post savePost(Post post){
        post.setCreatedAt(LocalDateTime.now());
        post.setModifiedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

}

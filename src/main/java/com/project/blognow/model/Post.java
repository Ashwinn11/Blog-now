package com.project.blognow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column (columnDefinition = "TEXT")
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @NotNull
    @ManyToOne
    @JoinColumn(name ="account_id",referencedColumnName = "id",nullable = false)
    private Account account;
}

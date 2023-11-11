package com.project.blognow.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @OneToMany(mappedBy = "account")
    private List<Post> posts;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "account_authority", joinColumns = {@JoinColumn (name = "account_id",referencedColumnName = "id")}
            ,inverseJoinColumns ={@JoinColumn (name = "authority_name",referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authoritySet=" + authorities +
                '}';
    }
}

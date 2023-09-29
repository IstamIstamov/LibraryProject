package com.company.LibraryProject.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = ("users"))
@NamedQuery(name = "findUser", query = "select u from User as u where u.userId = :id")
public class User {
    @Id
    @GeneratedValue(generator = "user_seq_id")
    @SequenceGenerator(name = "user_seq_id", sequenceName = "user_seq_id", allocationSize = 1)
    private Integer userId;//userid
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private Boolean enabled;


    @OneToMany(mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Card> cards;

    @OneToMany(mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Orders> orders;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<Authorities> authority;

    private LocalDateTime birthdate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

package com.company.LibraryProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("cards"))
public class Card {
    @Id
    @Column(name = ("card_id"))
    @GeneratedValue(generator = "card_seq_id")
    @SequenceGenerator(name = "card_seq_id",sequenceName = "card_seq_id",allocationSize = 1)
    private Integer cardId;

    @Column(name = ("card_name"))
    private String cardName;

    @Column(name = ("card_number"))
    private Long cardNumber;

    @Column(name = ("user_id"))
    private Integer userId;

    @Column(name = ("created_At"))
    private LocalDateTime createdAt;

    @Column(name = ("updated_At"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_At"))
    private LocalDateTime deletedAt;


}

package com.backendquizplatform.quiz_assignment.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Quiz_table")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String question;

    @ElementCollection
    @CollectionTable(name = "quiz_options", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<String> options;

    @Column(nullable = false)
    private int rightAnswer;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime startDate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime endDate;
    //
    @Column(nullable = false)
    private String status;

}

package com.practice.QuizApplication.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question_title")
    private String questionTitle;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Column(name = "difficult_level")
    private String difficultLevel;
    private String category;

}

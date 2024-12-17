package com.telusko.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "category", nullable = false)
    @JsonProperty("category")
    private String category;

    @Column(name = "difficulty_level", nullable = false)
    @JsonProperty("difficulty")
    private String difficultyLevel;

    @Column(name = "option_1", nullable = false)
    @JsonProperty("option_1")
    private String option1;

    @Column(name = "option_2", nullable = false)
    @JsonProperty("option_2")
    private String option2;

    @Column(name = "option_3", nullable = false)
    @JsonProperty("option_3")
    private String option3;

    @Column(name = "option_4", nullable = false)
    @JsonProperty("option_4")
    private String option4;

    @Column(name = "question_title", nullable = false)
    @JsonProperty("question_title")
    private String questionTitle;

    @Column(name = "right_answer", nullable = false)
    @JsonProperty("right_answer")
    private String rightAnswer;

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
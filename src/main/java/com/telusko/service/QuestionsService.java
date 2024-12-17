package com.telusko.service;

import com.telusko.entity.Questions;
import com.telusko.payload.Answers;
import com.telusko.payload.QuestionsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionsService {
    List<Questions> getAllQuestions();

    List<Questions> getQuestionsByCategory(String category);

    Questions createQuestion(Questions question);

    void deleteQuestion(Long id);

    ResponseEntity<?> getQuestionForQuiz(String category, Long numOfQuestion);

    List<QuestionsDto> getQuestionsByIds(List<Long> questionIds);

    Integer getScore(List<Answers> answers);
}

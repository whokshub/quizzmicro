package com.telusko.service;

import com.telusko.entity.Questions;
import com.telusko.payload.Answers;
import com.telusko.payload.QuestionsDto;
import com.telusko.repository.QuestionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private QuestionsRepository questionsRepository;

    public QuestionsServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public List<Questions> getAllQuestions() {
        List<Questions> all = questionsRepository.findAll();

        System.out.println(all);

        return all;
    }

    @Override
    public List<Questions> getQuestionsByCategory(String category) {

        List<Questions> byCategory = questionsRepository.findByCategory(category);
        return  byCategory;

    }

    @Override
    public Questions createQuestion(Questions question) {
        Questions save = questionsRepository.save(question);
        return save;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionsRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> getQuestionForQuiz(String category, Long numOfQuestion) {
        List<Integer> randomQuestionsByCategory = findRandomQuestionsByCategory(category, numOfQuestion);
        if (!randomQuestionsByCategory.isEmpty()) {
            return ResponseEntity.ok(randomQuestionsByCategory);
        }else {
            return new ResponseEntity<>("No questions found for this category", HttpStatus.NO_CONTENT);
        }
    }

    public List<Integer> findRandomQuestionsByCategory(String category, Long numQ) {
        List<Integer> questionIdByCategory = questionsRepository.findIdsByCategory(category);
        Collections.shuffle(questionIdByCategory); // Randomize the list in Java
        return questionIdByCategory.stream().limit(numQ).toList(); // Limit the results
    }


    @Override
    public List<QuestionsDto> getQuestionsByIds(List<Long> questionIds) {

        List<QuestionsDto> questionsDtos = new ArrayList<>();
        List<Questions> questions = new ArrayList<>();

        for (Long id : questionIds) {
            questions.add(questionsRepository.findById(id).get());
        }
        for (Questions q : questions){

            QuestionsDto qd = new QuestionsDto(
                    q.getId(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4(),
                    q.getQuestionTitle());
            questionsDtos.add(qd);
        }

        return questionsDtos;
    }

    @Override
    public Integer getScore(List<Answers> answers) {
        int score = 0;
        for(Answers ans : answers) {
            Questions questions = questionsRepository.findById(ans.getId()).get();
            if(ans.getAnswer().equals(questions.getRightAnswer())){
                score++;
            }
        }
        return score;
    }

}

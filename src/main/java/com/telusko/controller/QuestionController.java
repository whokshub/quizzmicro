package com.telusko.controller;


import com.telusko.entity.Questions;
import com.telusko.payload.Answers;
import com.telusko.payload.QuestionsDto;
import com.telusko.service.QuestionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private QuestionsService questionsService;

    public QuestionController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("allquestions")
    public ResponseEntity<List<Questions>> getQuestion(){

        List<Questions> allQuestions = questionsService.getAllQuestions();
        return new ResponseEntity<>(allQuestions, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable  String category){

         List<Questions> questionsByCategory = questionsService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questionsByCategory, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions question){

        Questions createdQuestion = questionsService.createQuestion(question);

        return new ResponseEntity<>(createdQuestion,HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){

        questionsService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("generate")
    public ResponseEntity<?> getQuestionForQuiz(@RequestParam String category, @RequestParam Long numOfQuestion){
         return questionsService.getQuestionForQuiz(category, numOfQuestion);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionsDto>> getQuestions(@RequestBody List<Long> questionIds){

        List<QuestionsDto> questionsByIds = questionsService.getQuestionsByIds(questionIds);
        return new ResponseEntity<>(questionsByIds, HttpStatus.OK);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answers> answers){

        Integer score = questionsService.getScore(answers);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}

package com.telusko.repository;

import com.telusko.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    List<Questions> findByCategory(String category);

    // Custom query to fetch IDs by category
    @Query("SELECT q.id FROM Questions q WHERE q.category = :category")
    List<Integer> findIdsByCategory(String category);

//    @Query(value="SELECT * FROM QUESTIONS q WHERE q.category=:cat ORDER BY RAND() LIMIT:numQ",nativeQuery = true)
//    List<Questions> findRandomQuestionsByCategory(@Param("cat") String category,@Param("numQ") Integer numQ);


}
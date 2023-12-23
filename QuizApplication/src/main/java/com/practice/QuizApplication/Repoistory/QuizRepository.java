package com.practice.QuizApplication.Repoistory;

import com.practice.QuizApplication.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}

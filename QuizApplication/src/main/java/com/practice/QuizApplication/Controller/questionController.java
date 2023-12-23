package com.practice.QuizApplication.Controller;

import com.practice.QuizApplication.Model.Question;
import com.practice.QuizApplication.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class questionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/Category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.QuestionByCategory(category);
    }

    @PostMapping("/post-Question")
    public ResponseEntity<String> AddNewQuestion(@RequestBody  Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/change-Question/{id}")
    public ResponseEntity<String> changeQuestion(@PathVariable int id, @RequestBody  Question question){
        return questionService.addById(id);
    }

    @GetMapping("/ById/{id}")
    public Question getStudentById(@PathVariable int id){
        return questionService.getStudentById(id);
    }

    @PutMapping("/Update/{id}")
    public Question UpdateStudentInfo(@PathVariable int id,@RequestBody Question question){
        return questionService.toUpdateStudent(id, question);
    }

    @DeleteMapping("/delete-question/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }
}

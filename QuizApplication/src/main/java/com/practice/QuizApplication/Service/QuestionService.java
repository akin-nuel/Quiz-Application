package com.practice.QuizApplication.Service;

import com.practice.QuizApplication.Model.Question;
import com.practice.QuizApplication.Repoistory.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question){

        try {
            questionRepository.save(question);
            return new ResponseEntity<>("Question Successfully added", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addById(int id){
        Question update = questionRepository.findById(id).orElseThrow(null);

        try {
            questionRepository.save(update);
            return new ResponseEntity<>("Question Successfully added", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> QuestionByCategory(String category){
        return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
    }

    public Question getStudentById(int id){
        return questionRepository.findById(id).orElseThrow(null);
    }



    public Question toUpdateStudent(int id, Question question){
        Question toUpdate = questionRepository.findById(id).orElseThrow(null);

        toUpdate.setQuestionTitle(question.getQuestionTitle());
        toUpdate.setCategory(question.getCategory());
        toUpdate.setDifficultLevel(question.getDifficultLevel());
        toUpdate.setOption1(question.getOption1());
        toUpdate.setOption2(question.getOption2());
        toUpdate.setOption3(question.getOption3());
        toUpdate.setOption4(question.getOption4());
        toUpdate.setCategory(question.getCategory());
        toUpdate.setRightAnswer(question.getRightAnswer());

        return questionRepository.save(toUpdate);
    }

    public String deleteQuestion(int id){
        Question toDelete = getStudentById(id);

        questionRepository.delete(toDelete);
        return "Question Successfully Deleted";
    }
}

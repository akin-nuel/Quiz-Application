package com.practice.QuizApplication.Service;

import com.practice.QuizApplication.Model.Question;
import com.practice.QuizApplication.Model.QuestionWrapper;
import com.practice.QuizApplication.Model.Quiz;
import com.practice.QuizApplication.Model.Response;
import com.practice.QuizApplication.Repoistory.QuestionRepository;
import com.practice.QuizApplication.Repoistory.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuestion(String category, int numQ, String title) {
        List<Question> quizQuestions = questionRepository.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(quizQuestions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestion();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        int right = 0;
        int i = 0;
        for(Response response: responses){
            if(response.getUserResponse().equals(questions.get(i).getRightAnswer()));
                right++;

            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}

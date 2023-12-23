package com.practice.QuizApplication.Exception;

public class QuizNotFound extends RuntimeException{
    public QuizNotFound(String message){
        super(message);
    }
}

package com.backendquizplatform.quiz_assignment.Scheduling;

import com.backendquizplatform.quiz_assignment.Models.Quiz;
import com.backendquizplatform.quiz_assignment.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class QuizStatusUpdater {

    private final QuizRepository quizRepository;
    @Autowired
    public QuizStatusUpdater(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    @Scheduled(cron = "O * * * * *") // runs every minute
    public void updateQuizStatus(){
//        get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // update quiz status based on start and end time
        List<Quiz> quizzes = quizRepository.findAll();
        for (Quiz quiz : quizzes) {
            if (quiz.getStartDate().isAfter(currentDateTime)) {
                quiz.setStatus("inactive");
            } else if (quiz.getEndDate().isBefore(currentDateTime)) {
                quiz.setStatus("finished");
            } else {
                quiz.setStatus("active");
            }
        }
        quizRepository.saveAll(quizzes);
    }
}
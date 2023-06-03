package com.backendquizplatform.quiz_assignment.Controllers;

import com.backendquizplatform.quiz_assignment.Models.Quiz;
import com.backendquizplatform.quiz_assignment.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
//    Inject quizRepository to controller using Autowired

    private final QuizRepository quizRepository;

    @Autowired
    public QuizController(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {

        // Save the quiz to the database using the repository
       
        Quiz savedQuiz = quizRepository.save(quiz);
        return ResponseEntity.ok(savedQuiz);   //status 200
    }


    @GetMapping("/active")
    public ResponseEntity<Quiz> getActiveQuiz() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Find the active quiz using the repository method
        Optional<Quiz> activeQuiz = quizRepository.findFirstByStartDateBeforeAndEndDateAfter(currentDateTime, currentDateTime);

        if (activeQuiz.isPresent()) {
            return ResponseEntity.ok(activeQuiz.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<String> getQuizResult(@PathVariable("id") Long id) {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Find the quiz by ID and check if the quiz is finished
        Optional<Quiz> quiz = quizRepository.findByIdAndEndDateBefore(id, currentDateTime);

        if (quiz.isPresent()) {
            return ResponseEntity.ok("The result of the quiz is: " + quiz.get().getRightAnswer());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        // Retrieve all quizzes from the database using the repository
        List<Quiz> quizzes = quizRepository.findAll();
        return ResponseEntity.ok(quizzes);
    }

}


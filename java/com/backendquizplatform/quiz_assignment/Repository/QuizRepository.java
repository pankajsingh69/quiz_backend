package com.backendquizplatform.quiz_assignment.Repository;

import com.backendquizplatform.quiz_assignment.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findFirstByStartDateBeforeAndEndDateAfter(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Quiz> findByIdAndEndDateBefore(Long id, LocalDateTime currentDateTime);

}

package be.ipam.learnalanguage.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttemptDto {
    private Long id;
    private Long userId;
    private Long listId;
    private int totalQuestions;
    private int correctAnswers;
    private int scorePercent;
    private LocalDateTime createdAt;
}

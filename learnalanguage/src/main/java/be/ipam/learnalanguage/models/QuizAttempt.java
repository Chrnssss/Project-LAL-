package be.ipam.learnalanguage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempt")
@Getter
@Setter
@NoArgsConstructor
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    private VocabularyList list;

    @Column(name = "total_questions", nullable = false)
    private int totalQuestions;

    @Column(name = "correct_answers", nullable = false)
    private int correctAnswers;

    @Column(name = "score_percent", nullable = false)
    private int scorePercent;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}

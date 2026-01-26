package be.ipam.learnalanguage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "translation")
@Getter
@Setter
@NoArgsConstructor
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}


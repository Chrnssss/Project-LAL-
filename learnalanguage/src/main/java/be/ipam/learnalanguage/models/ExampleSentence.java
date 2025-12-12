package be.ipam.learnalanguage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "example_sentence")
@Getter
@Setter
@NoArgsConstructor
public class ExampleSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "translation_id", nullable = false)
    private Translation translation;

    @Column(name = "example_text", nullable = false, length = 500)
    private String exampleText;

    @Column(name = "example_translation", length = 500)
    private String exampleTranslation;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}


package be.ipam.learnalanguage.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewLogDto {
    private Long id;
    private Long userId;
    private Long flashcardId;
    private boolean success;
    private LocalDateTime createdAt;
}


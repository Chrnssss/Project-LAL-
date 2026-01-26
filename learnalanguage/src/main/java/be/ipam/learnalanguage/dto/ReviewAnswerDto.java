package be.ipam.learnalanguage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAnswerDto {
    private Long userId;
    private Long flashcardId;
    private boolean success;
}

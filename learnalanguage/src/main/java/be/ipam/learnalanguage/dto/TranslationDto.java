package be.ipam.learnalanguage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationDto {
    private Long id;
    private Long flashcardId;
    private Long languageId;
    private String text;
}


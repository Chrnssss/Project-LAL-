package be.ipam.learnalanguage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabularyListDto {
    private Long id;
    private Long languageId;
    private String title;
    private String description;
    private boolean isPublic;
}


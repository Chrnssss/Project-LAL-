package be.ipam.learnalanguage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleSentenceDto {
    private Long id;
    private Long translationId;
    private String exampleText;
    private String exampleTranslation;
}


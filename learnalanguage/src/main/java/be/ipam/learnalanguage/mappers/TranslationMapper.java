package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.TranslationDto;
import be.ipam.learnalanguage.models.Translation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TranslationMapper {

    @Mapping(target = "flashcardId", source = "flashcard.id")
    @Mapping(target = "languageId", source = "language.id")
    TranslationDto toDto(Translation entity);

    List<TranslationDto> toDtoList(List<Translation> entities);
}

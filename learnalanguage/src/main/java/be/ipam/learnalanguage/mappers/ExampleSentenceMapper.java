package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.ExampleSentenceDto;
import be.ipam.learnalanguage.models.ExampleSentence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExampleSentenceMapper {

    @Mapping(target = "translationId", source = "translation.id")
    ExampleSentenceDto toDto(ExampleSentence entity);

    List<ExampleSentenceDto> toDtoList(List<ExampleSentence> entities);
}

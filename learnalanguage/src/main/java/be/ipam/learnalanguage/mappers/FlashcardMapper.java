package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.FlashcardDto;
import be.ipam.learnalanguage.models.Flashcard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlashcardMapper {

    @Mapping(target = "listId", source = "list.id")
    FlashcardDto toDto(Flashcard entity);

    List<FlashcardDto> toDtoList(List<Flashcard> entities);
}

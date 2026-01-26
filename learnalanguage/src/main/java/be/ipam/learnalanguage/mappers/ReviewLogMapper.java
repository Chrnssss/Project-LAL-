package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.ReviewLogDto;
import be.ipam.learnalanguage.models.ReviewLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewLogMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "flashcardId", source = "flashcard.id")
    ReviewLogDto toDto(ReviewLog entity);

    List<ReviewLogDto> toDtoList(List<ReviewLog> entities);
}

package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.QuizAttemptDto;
import be.ipam.learnalanguage.models.QuizAttempt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizAttemptMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "listId", source = "list.id")
    QuizAttemptDto toDto(QuizAttempt entity);

    List<QuizAttemptDto> toDtoList(List<QuizAttempt> entities);
}

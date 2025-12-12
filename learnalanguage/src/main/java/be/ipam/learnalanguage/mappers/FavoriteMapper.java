package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.FavoriteDto;
import be.ipam.learnalanguage.models.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "flashcardId", source = "flashcard.id")
    FavoriteDto toDto(Favorite entity);

    List<FavoriteDto> toDtoList(List<Favorite> entities);
}

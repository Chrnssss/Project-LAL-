package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.VocabularyListDto;
import be.ipam.learnalanguage.models.VocabularyList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VocabularyListMapper {

    @Mapping(target = "languageId", source = "language.id")
    VocabularyListDto toDto(VocabularyList entity);

    List<VocabularyListDto> toDtoList(List<VocabularyList> entities);
}

package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.LanguageDto;
import be.ipam.learnalanguage.models.Language;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LanguageMapper {
    @Mapping(target = "id", ignore = true)
    Language toEntity(LanguageDto languageDto);

    LanguageDto toDto(Language language);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Language partialUpdate(LanguageDto languageDto, @MappingTarget Language language);

    List<LanguageDto> toDtoList(List<Language> languages);
}
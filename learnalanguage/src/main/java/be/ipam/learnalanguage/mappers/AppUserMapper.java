package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.AppUserDto;
import be.ipam.learnalanguage.models.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDto toDto(AppUser entity);

    List<AppUserDto> toDtoList(List<AppUser> entities);
}

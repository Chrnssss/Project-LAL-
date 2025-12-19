package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.NotificationDto;
import be.ipam.learnalanguage.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "userId", source = "user.id")
    NotificationDto toDto(Notification entity);

    List<NotificationDto> toDtoList(List<Notification> entities);
}

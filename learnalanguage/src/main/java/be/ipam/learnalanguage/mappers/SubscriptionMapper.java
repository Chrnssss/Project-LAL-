package be.ipam.learnalanguage.mappers;

import be.ipam.learnalanguage.dto.SubscriptionDto;
import be.ipam.learnalanguage.models.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "listId", source = "list.id")
    SubscriptionDto toDto(Subscription entity);

    List<SubscriptionDto> toDtoList(List<Subscription> entities);
}

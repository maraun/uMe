package kz.u.u.uMe.models.mappers;

import kz.u.u.uMe.models.dtos.GroupTypeDto;
import kz.u.u.uMe.models.entities.GroupType;
import kz.u.u.uMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class GroupTypeMapper extends AbstractModelMapper<GroupType, GroupTypeDto> {

    private ModelMapper modelMapper;

    @Autowired
    public GroupTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupTypeDto toDto(GroupType groupType) {
        return modelMapper.map(groupType, GroupTypeDto.class);
    }

    @Override
    public GroupType toEntity(GroupTypeDto groupTypeDto) {
        return modelMapper.map(groupTypeDto, GroupType.class);
    }

    @Override
    public List<GroupTypeDto> toDtoList(List<GroupType> groupTypes) {

        return groupTypes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupType> toEntityList(List<GroupTypeDto> groupTypeDtos) {
        return groupTypeDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

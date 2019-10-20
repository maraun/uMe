package kz.u.u.uMe.models.mappers;

import kz.u.u.uMe.models.dtos.GroupDto;
import kz.u.u.uMe.models.entities.Group;
import kz.u.u.uMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper extends AbstractModelMapper<Group, GroupDto> {

    private ModelMapper modelMapper;
    private GroupTypeMapper groupTypeMapper;

    @Autowired
    public GroupMapper(ModelMapper modelMapper,
                       GroupTypeMapper groupTypeMapper) {
        this.modelMapper = modelMapper;
        this.groupTypeMapper = groupTypeMapper;
    }

    @Override
    public GroupDto toDto(Group group) {
        GroupDto groupDto = modelMapper.map(group, GroupDto.class);
        if (group.getGroupType() != null) {
            groupDto.setGroupType(groupTypeMapper.toDto(group.getGroupType()));
        }
        return groupDto;
    }

    @Override
    public Group toEntity(GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        if (groupDto.getGroupType() != null) {
            group.setGroupType(groupTypeMapper.toEntity(groupDto.getGroupType()));
        }
        return group;
    }

    @Override
    public List<GroupDto> toDtoList(List<Group> groups) {
        return groups.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Group> toEntityList(List<GroupDto> groupDtos) {
        return groupDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

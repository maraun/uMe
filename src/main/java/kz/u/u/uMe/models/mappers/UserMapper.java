package kz.u.u.uMe.models.mappers;

import kz.u.u.uMe.models.dtos.UserDto;
import kz.u.u.uMe.models.entities.User;
import kz.u.u.uMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper extends AbstractModelMapper<User, UserDto> {

    private ModelMapper modelMapper;
    private RoleMapper roleMapper;
    private ProfileMapper profileMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper,
                      RoleMapper roleMapper,
                      ProfileMapper profileMapper) {
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;
        this.profileMapper=profileMapper;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if (user.getRoles() != null) {
            userDto.setRoles(roleMapper.toDtoSet(user.getRoles()));
        }
        if (user.getProfile() != null) {
            userDto.setProfile(profileMapper.toDto(user.getProfile()));
        }
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRoles() != null) {
            user.setRoles(roleMapper.toEntitySet(userDto.getRoles()));
        }
        if (userDto.getProfile() != null) {
            user.setProfile(profileMapper.toEntity(userDto.getProfile()));
        }
        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

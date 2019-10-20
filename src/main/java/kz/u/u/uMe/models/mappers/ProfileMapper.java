package kz.u.u.uMe.models.mappers;

import kz.u.u.uMe.models.dtos.ProfileDto;
import kz.u.u.uMe.models.entities.Profile;
import kz.u.u.uMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper extends AbstractModelMapper<Profile, ProfileDto> {

    private ModelMapper modelMapper;
    private PositionMapper positionMapper;

    @Autowired
    public ProfileMapper(ModelMapper modelMapper,
                         PositionMapper positionMapper) {
        this.modelMapper = modelMapper;
        this.positionMapper = positionMapper;
    }

    @Override
    public ProfileDto toDto(Profile profile) {
        ProfileDto profileDto = modelMapper.map(profile, ProfileDto.class);
        if (profile.getPositions() != null) {
            profileDto.setPositions(positionMapper.toDtoSet(profile.getPositions()));
        }
        return profileDto;
    }

    @Override
    public Profile toEntity(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        if (profileDto.getPositions() != null) {
            profile.setPositions(positionMapper.toEntitySet(profileDto.getPositions()));
        }
        return profile;
    }

    @Override
    public List<ProfileDto> toDtoList(List<Profile> profiles) {
        return profiles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDto> profileDtos) {
        return profileDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

package kz.u.u.uMe.models.mappers;

import kz.u.u.uMe.models.dtos.PositionDto;
import kz.u.u.uMe.models.entities.Position;
import kz.u.u.uMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class PositionMapper extends AbstractModelMapper<Position, PositionDto> {

    private ModelMapper modelMapper;

    @Autowired
    public PositionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PositionDto toDto(Position position) {
        return modelMapper.map(position, PositionDto.class);
    }

    @Override
    public Position toEntity(PositionDto positionDto) {
        return modelMapper.map(positionDto, Position.class);
    }

    @Override
    public List<PositionDto> toDtoList(List<Position> positions) {

        return positions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Position> toEntityList(List<PositionDto> positionDtos) {
        return positionDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public Set<PositionDto> toDtoSet(Set<Position> positions) {

        return positions.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }


    public Set<Position> toEntitySet(Set<PositionDto> positionDtos) {
        return positionDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}

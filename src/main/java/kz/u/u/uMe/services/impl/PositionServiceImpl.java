package kz.u.u.uMe.services.impl;

import kz.u.u.uMe.exceptions.ServiceException;

import kz.u.u.uMe.models.entities.Position;
import kz.u.u.uMe.repositories.PositionRepository;
import kz.u.u.uMe.services.PositionService;
import kz.u.u.uMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position findById(Long id) throws ServiceException {
        Optional<Position> positionOptional = positionRepository.findByDeletedAtIsNullAndId(id);
        return positionOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("position not found")
                .build());
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException{
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Position position = findById(id);
        position.setDeletedAt(new Date());
        positionRepository.save(position);
    }

    @Override
    public Position save(Position position) throws ServiceException{
        if (position.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("position already exists")
                    .build();
        }
        return positionRepository.save(position);
    }

    @Override
    public Position update(Position position) throws ServiceException{
        if (position.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("position is null")
                    .build();
        }
        return positionRepository.save(position);
    }
}

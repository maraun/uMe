package kz.u.u.uMe.services;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Position;

import java.util.List;

public interface PositionService {
    Position findById(Long id) throws ServiceException;
    List<Position> findAll();
    void deleteById(Long id) throws ServiceException;
    Position save(Position position) throws ServiceException;
    Position update(Position position) throws ServiceException;
}

package kz.u.u.uMe.services;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Group;

import java.util.List;

public interface GroupService {
    Group findById(Long id) throws ServiceException;
    List<Group> findAll();
    void deleteById(Long id) throws ServiceException;
    Group save(Group group) throws ServiceException;
    Group update(Group group) throws ServiceException;
}

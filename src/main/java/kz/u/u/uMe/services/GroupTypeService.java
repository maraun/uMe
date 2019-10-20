package kz.u.u.uMe.services;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.GroupType;

import java.util.List;

public interface GroupTypeService {
    GroupType findById(Long id) throws ServiceException;
    List<GroupType> findAll();
    void deleteById(Long id) throws ServiceException;
    GroupType save(GroupType groupType) throws ServiceException;
    GroupType update(GroupType groupType) throws ServiceException;
}

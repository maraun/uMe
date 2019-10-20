package kz.u.u.uMe.services;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Profile;

import java.util.List;

public interface ProfileService {
    Profile findById(Long id) throws ServiceException;
    List<Profile> findAll();
    void deleteById(Long id) throws ServiceException;
    Profile save(Profile profile) throws ServiceException;
    Profile update(Profile profile) throws ServiceException;
}

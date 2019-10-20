package kz.u.u.uMe.services.impl;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Profile;
import kz.u.u.uMe.repositories.ProfileRepository;
import kz.u.u.uMe.services.ProfileService;
import kz.u.u.uMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile findById(Long id) throws ServiceException {
        Optional<Profile> profileOptional = profileRepository.findByDeletedAtIsNullAndId(id);
        return profileOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("profile not found")
                .build());
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException{
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Profile profile = findById(id);
        profile.setDeletedAt(new Date());
        profileRepository.save(profile);
    }

    @Override
    public Profile save(Profile profile) throws ServiceException{
        if (profile.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("profile already exists")
                    .build();
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Profile profile) throws ServiceException{
        if (profile.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("profile is null")
                    .build();
        }
        return profileRepository.save(profile);
    }
}

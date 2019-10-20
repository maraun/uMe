package kz.u.u.uMe.services.impl;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.GroupType;
import kz.u.u.uMe.repositories.GroupTypeRepository;
import kz.u.u.uMe.services.GroupTypeService;
import kz.u.u.uMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupTypeServiceImpl implements GroupTypeService {

    private GroupTypeRepository groupTypeRepository;

    @Autowired
    public GroupTypeServiceImpl(GroupTypeRepository groupTypeRepository) {
        this.groupTypeRepository = groupTypeRepository;
    }

    @Override
    public GroupType findById(Long id) throws ServiceException {
        Optional<GroupType> groupTypeOptional = groupTypeRepository.findByDeletedAtIsNullAndId(id);
        return groupTypeOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("group not found")
                .build());
    }

    @Override
    public List<GroupType> findAll() {
        return groupTypeRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException{
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        GroupType groupType = findById(id);
        groupType.setDeletedAt(new Date());
        groupTypeRepository.save(groupType);
    }

    @Override
    public GroupType save(GroupType groupType) throws ServiceException{
        if (groupType.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("grouptype already exists")
                    .build();
        }
        return groupTypeRepository.save(groupType);
    }

    @Override
    public GroupType update(GroupType groupType) throws ServiceException{
        if (groupType.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("grouptype is null")
                    .build();
        }
        return groupTypeRepository.save(groupType);
    }
}

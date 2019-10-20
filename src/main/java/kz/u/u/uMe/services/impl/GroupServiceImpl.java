package kz.u.u.uMe.services.impl;

import kz.u.u.uMe.exceptions.ServiceException;
import kz.u.u.uMe.models.entities.Group;
import kz.u.u.uMe.repositories.GroupRepository;
import kz.u.u.uMe.services.GroupService;
import kz.u.u.uMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group findById(Long id) throws ServiceException {
        Optional<Group> groupOptional = groupRepository.findByDeletedAtIsNullAndId(id);
        return groupOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("group not found")
                .build());
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException{
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Group group = findById(id);
        group.setDeletedAt(new Date());
        groupRepository.save(group);
    }

    @Override
    public Group save(Group group) throws ServiceException{
        if (group.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("group already exists")
                    .build();
        }
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) throws ServiceException{
        if (group.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("group is null")
                    .build();
        }
        return groupRepository.save(group);
    }
}

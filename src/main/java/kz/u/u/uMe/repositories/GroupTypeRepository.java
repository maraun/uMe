package kz.u.u.uMe.repositories;

import kz.u.u.uMe.models.entities.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupTypeRepository extends JpaRepository<GroupType, Long> {

    Optional<GroupType> findByDeletedAtIsNullAndId(Long id);
    List<GroupType> findAllByDeletedAtIsNull();
}

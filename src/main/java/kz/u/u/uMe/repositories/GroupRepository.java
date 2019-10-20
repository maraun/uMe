package kz.u.u.uMe.repositories;

import kz.u.u.uMe.models.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByDeletedAtIsNullAndId(Long id);
    List<Group> findAllByDeletedAtIsNull();
}

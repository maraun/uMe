package kz.u.u.uMe.repositories;

import kz.u.u.uMe.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findAllByDeletedAtIsNull();
}

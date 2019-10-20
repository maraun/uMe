package kz.u.u.uMe.repositories;

import kz.u.u.uMe.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByDeletedAtIsNullAndId(Long id);
    List<Profile> findAllByDeletedAtIsNull();
}

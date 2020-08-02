package pl.sda.JobOfferApplication.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   boolean existsByLogin(String login);
}

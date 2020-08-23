package pl.sda.JobOfferApplication.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.JobOfferApplication.user.entity.JobOfferEntity;

public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Long> {
}

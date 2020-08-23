package pl.sda.JobOfferApplication.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "JOBOFFERS")
@NoArgsConstructor
@Getter
public class JobOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobOfferName;
    private String categoryName;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public JobOfferEntity(String jobOfferName, String categoryName, LocalDate startDate, LocalDate endDate, UserEntity userEntity) {
        this.jobOfferName = jobOfferName;
        this.categoryName = categoryName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userEntity = userEntity;
    }
}

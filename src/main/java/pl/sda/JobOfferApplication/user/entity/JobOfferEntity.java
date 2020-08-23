package pl.sda.JobOfferApplication.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.JobOfferApplication.user.model.Category;
import pl.sda.JobOfferApplication.user.model.JobOfferOutput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

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

    public JobOfferOutput convertJobOfferEntityToOutput(){
        String name = userEntity.GetName();
        return new JobOfferOutput(jobOfferName, Category.valueOf(categoryName), startDate, endDate, name);
    }
}

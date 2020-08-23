package pl.sda.JobOfferApplication.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobOfferInput {

    private String jobOfferName;
    private Category category;
    private String startDate;
    private String endDate;
    private Long userId;

    public JobOfferInput(String offerName, String category, String startDate, String endDate, Long userId) {
        this.jobOfferName = offerName;
        this.category = Category.valueOf(category);
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }
}

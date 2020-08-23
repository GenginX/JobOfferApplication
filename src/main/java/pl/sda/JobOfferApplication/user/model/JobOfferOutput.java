package pl.sda.JobOfferApplication.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
public class JobOfferOutput {

    private String jobOfferName;
    private Category category;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;

}
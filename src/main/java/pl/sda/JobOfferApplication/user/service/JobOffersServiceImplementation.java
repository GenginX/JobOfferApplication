package pl.sda.JobOfferApplication.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.JobOfferApplication.user.Repository.JobOfferRepository;
import pl.sda.JobOfferApplication.user.entity.JobOfferEntity;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.JobOfferInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class JobOffersServiceImplementation implements JobOffersService {

    private JobOfferRepository jobOffersRepository;
    private UserService userService;

    @Autowired
    public JobOffersServiceImplementation(JobOfferRepository jobOffersRepository, UserService userService) {
        this.jobOffersRepository = jobOffersRepository;
        this.userService = userService;
    }

    @Override
    public void createJobOffer(JobOfferInput jobOfferInput) throws UserException {
        Long userId = jobOfferInput.getUserId();
        UserEntity userEntity = userService.getUserEntity(userId);


        jobOffersRepository.save(new JobOfferEntity(
                jobOfferInput.getJobOfferName(),
                jobOfferInput.getCategory().getName(),
                convertFromStringToLocalDate(jobOfferInput.getStartDate()),
                convertFromStringToLocalDate(jobOfferInput.getEndDate()),
                userEntity));
    }

    private LocalDate convertFromStringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}

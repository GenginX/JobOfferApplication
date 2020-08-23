package pl.sda.JobOfferApplication.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.JobOfferInput;
import pl.sda.JobOfferApplication.user.service.JobOffersService;

@RestController
@RequestMapping(JobOfferController.JOB_OFFERS)
public class JobOfferController {

    public static final String JOB_OFFERS = "/joboffers";
    @Autowired
    private JobOffersService jobOffersService;


    public JobOfferController(JobOffersService jobOffersService) {
        this.jobOffersService = jobOffersService;
    }

    @PostMapping
    public ResponseEntity<Void> createJobOffer(@RequestBody JobOfferInput jobOfferInput) throws UserException {
        jobOffersService.createJobOffer(jobOfferInput);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}

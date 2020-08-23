package pl.sda.JobOfferApplication.user.service;


import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.JobOfferInput;
import pl.sda.JobOfferApplication.user.model.UserInput;

public interface JobOffersService {
    public void createJobOffer(JobOfferInput jobOfferInput) throws UserException;

}

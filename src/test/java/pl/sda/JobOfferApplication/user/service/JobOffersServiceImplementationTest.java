package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.Repository.JobOfferRepository;
import pl.sda.JobOfferApplication.user.entity.JobOfferEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.JobOfferInput;
import pl.sda.JobOfferApplication.user.model.JobOfferOutput;
import pl.sda.JobOfferApplication.user.model.UserInput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JobOffersServiceImplementationTest {

    @Autowired
    JobOffersService jobOffersService;

    @Autowired
    JobOfferRepository jobOfferRepository;

    @Autowired
    UserService userService;

    @AfterEach
    void tearDown() {
        jobOfferRepository.deleteAll();
    }


    @Test
    public void isJobOferCreated() throws UserException {

        //given
        UserInput input = new UserInput("SDAblabla", "superuser", "SDAuser1@");
        userService.createUser(input);
        JobOfferInput jobOfferInput = new JobOfferInput("Pierwsza oferta", "IT", "23/08/2020", "25/08/2020", 1L);

        //when
        jobOffersService.createJobOffer(jobOfferInput);

        //then
        List<JobOfferEntity> createdJobOffers = jobOfferRepository.findAll();
        assertEquals(createdJobOffers.size(), 1L);
        JobOfferOutput output = createdJobOffers.get(0).convertJobOfferEntityToOutput();
        jobOfferInput.equals(output);

    }

    @Test
    public void unhappyPathIsJobOferCreated() throws UserException {

        //given
        JobOfferInput jobOfferInput = new JobOfferInput("Pierwsza oferta", "IT", "23/08/2020", "25/08/2020", 0L);

        //when
        Executable executable = () -> jobOffersService.createJobOffer(jobOfferInput);

        //then
        assertThrows(UserException.class, executable);

    }

}
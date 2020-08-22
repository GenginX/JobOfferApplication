package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.PasswordException;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void isUserCreated() throws UserException, PasswordException {

        //given
        UserInput input = new UserInput("SDAblabla", "superuser","SDAuser1@");
        //when
        userService.createUser(input);
        //then
        List<UserEntity> createdUsers = userRepository.findAll();
        assertEquals(createdUsers.size(), 1);
        UserOutput output = createdUsers.get(0).convertEntityToOutput();
        input.equals(output);

    }



}
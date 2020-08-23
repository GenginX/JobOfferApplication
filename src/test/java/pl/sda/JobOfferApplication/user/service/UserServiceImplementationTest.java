package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;



import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplementationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void getCorrectUserById() throws UserException {
        //given
        UserInput userInput = new UserInput("TestUser", "TestName", "TestPassword1!");
        UserInput userInput1 = new UserInput("TestUer1", "TestName1", "TestPassword1!");
        userService.createUser(userInput);
        userService.createUser(userInput1);
        Long usedId = 2L;

        //when
        UserOutput userById = userService.getUserById(usedId);

        //then
        assertEquals(userById.getId(),usedId);
    }

    @Test
    public void unHappyPathGetCorrectUserById() throws UserException {
        // given
        UserInput userInput = new UserInput("TestUser", "TestName", "TestPassword1");
        UserInput userInput1 = new UserInput("TestUer1", "TestName1", "TestPassword1");
        userService.createUser(userInput);
        userService.createUser(userInput1);
        Long usedId = 5L;

        // when
        Executable executable = () -> userService.getUserById(usedId);

        //then
        assertThrows(UserException.class, executable);
    }
}
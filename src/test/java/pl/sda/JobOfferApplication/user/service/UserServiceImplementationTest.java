package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import pl.sda.JobOfferApplication.user.entity.UserEntity;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.sda.JobOfferApplication.user.service.UserServiceImplementation.NO_USER_FOUND_FOR_GIVEN_ID;

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
    public void isUserCreated() throws UserException {

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

    @Test
    public void passwordValidation() throws UserException {

        try{
            UserInput input = new UserInput("SDAblabla", "superuser","SDA");
            userService.createUser(input);
            String usedPassword = input.getPassword();
            //UserServiceImplementation usi = new UserServiceImplementation(); instancja - odwołanie do metod z userServiceImplementation
        } catch (UserException e) {
            System.out.println(e.getMessage());
            //"Please provivide stronger password: length higher than 8, one big letter, one small letter, one digit, one special character"
        }
    }

    @Test
    public void userInputValidation() throws UserException {
        try {
            UserInput input = new UserInput("SDAblabla", "superuser", "SDAascdsvf1@#$");
            UserInput input2 = new UserInput("SDAblabla", "gyfrw", "njl1W223!@");
            userService.createUser(input);
            userService.createUser(input2);
        } catch (UserException e) {
            System.out.println(e.getMessage());
            //"User with this login, already exists"
        }
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
        UserInput userInput = new UserInput("TestUser", "TestName", "TestPassword1!");
        UserInput userInput1 = new UserInput("TestUser1", "TestName1", "TestPassword1!");
        userService.createUser(userInput);
        userService.createUser(userInput1);
        Long usedId = 5L;

        // when
        Executable executable = () -> userService.getUserById(usedId);

        //then
        assertThrows(UserException.class, executable);
    }

    @Test
    public void deleteUserById() throws UserException {
        //given
        UserInput userInput = new UserInput("Karoll", "Karol", "Piesek!1");
        userService.createUser(userInput);

        //when
        userService.deleteUserById(1L);

        //then
        List<UserEntity> allUsers = userRepository.findAll();
        assertTrue(allUsers.size() == 0);
    }

    @Test
    public void unhappyDeleteUserById() {
        //given

        //when
        Executable executable = () -> userService.deleteUserById(1L);

        //then
        UserException userException = assertThrows(UserException.class, executable);
        assertEquals(NO_USER_FOUND_FOR_GIVEN_ID, userException.getMessage());
    }
}
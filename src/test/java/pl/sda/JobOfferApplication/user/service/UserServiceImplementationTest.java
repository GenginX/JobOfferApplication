package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;



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
    void tearDown() {
        userRepository.deleteAll();
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
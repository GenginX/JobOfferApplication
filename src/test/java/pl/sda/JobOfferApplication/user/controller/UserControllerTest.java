package pl.sda.JobOfferApplication.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.JobOfferApplication.JobOfferApplication;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferApplication.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUserController() throws Exception {
        //given
        UserInput userInput = new UserInput("Adam123", "Adam", "Adam1234!");
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post(UserController.USERS_PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(userInput));

        //when
        final ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        //test
        resultActions.andExpect(status().isCreated());
    }

    private static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUserByIdController() throws Exception {
        // given
        UserInput userInput = new UserInput("Adam123", "Adam", "Adam1234!");
        UserInput userInput2 = new UserInput("Adam1234", "Adam2", "Adam1234!@");
        userService.createUser(userInput);
        userService.createUser(userInput2);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get(UserController.USERS_PATH + "/" + 2)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        //when
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void unHappyPathTestGetUserByIdController() throws Exception {
        //given
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get(UserController.USERS_PATH + "/" + 2)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        //when
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        //then
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void userDeletion() throws Exception {

        //given
        UserInput input = new UserInput("login1234", "Kuba", "Kubasdaq1!");
        userService.createUser(input);
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(UserController.USERS_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        //when
        final ResultActions resultActions = mockMvc.perform(requestBuilder);
        //then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void unhappyUserDeletion() throws Exception {

        //given

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(UserController.USERS_PATH + "/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        //when
        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(status().isNotFound());
    }

}
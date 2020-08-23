package pl.sda.JobOfferApplication.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;
import pl.sda.JobOfferApplication.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(UserController.USERS_PATH)
public class UserController {


    public static final String USERS_PATH = "/users";
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers(){
        final List<UserOutput> allUsers = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allUsers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable (value = "id") Long id) throws UserException {
        UserOutput userById = userService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userById);
    }
    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody UserInput userInput) throws UserException {
        userService.createUser(userInput);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                build();
    }

//    W ramach aplikacji powinien zostać udostępniony end-point umożliwiający usunięcie dowolnego użytkownika po jego ID.
//
//    Założenia:
//            - Uzytkownik musi istnieć aby mażna było go usunąć. Poprawne wykonanie operacji:  HTTP 204 NO_CONTENT
//            - Brak podanego do usunięcia użytkownika w systemie:                              HTTP 404  NOT_FOUND

    @DeleteMapping("/{id}")
    public ResponseEntity<UserOutput> deleteUserById(@PathVariable (value = "id") Long id) throws UserException {
        userService.deleteUserById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();

    }

}

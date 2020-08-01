package pl.sda.JobOfferApplication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers(){
        UserOutput userOutput = new UserOutput(1L,"Admin", "Admin", "1212");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.singletonList(userOutput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable (value = "id") Long id){
        final UserOutput userOutput = new UserOutput(1L,"Admin", "Admin", "1212");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userOutput);
    }
    @PostMapping
    public ResponseEntity<Void> postUser(@RequestBody UserInput userInput){
        System.out.println(userInput);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                build();
    }


}

package pl.sda.JobOfferApplication.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;


@Getter
@ToString
public class UserInput {

    private String login;
    private String name;
    @JsonIgnore
    private final LocalDate creationDate = LocalDate.now();
    private String password;


    public UserInput(String login, String name, String password){
        this.login = login;
        this.name = name;
        this.password = password;
    }

}

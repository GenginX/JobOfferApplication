package pl.sda.JobOfferApplication.user.model;

import lombok.*;

import java.time.LocalDate;


@Getter
@ToString
public class UserInput {

    private String login;
    private String name;
    private LocalDate creationDate;
    private String password;

    private UserInput() {
        creationDate = LocalDate.now();
    }

    public UserInput(String login, String name, String password){
        this.login = login;
        this.name = name;
        this.creationDate = LocalDate.now();
        this.password = password;
    }

}

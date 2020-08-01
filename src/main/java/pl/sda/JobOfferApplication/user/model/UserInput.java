package pl.sda.JobOfferApplication.user.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@ToString
public class UserInput {
    private String  uuid;
    private String login;
    private String name;
    private String creationDate;
    private String password;

    private UserInput() {
        uuid = UUID.randomUUID().toString();
        creationDate = "now";
    }
}

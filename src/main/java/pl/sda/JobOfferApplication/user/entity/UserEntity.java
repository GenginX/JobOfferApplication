package pl.sda.JobOfferApplication.user.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String name;
    private LocalDate creationDate;
    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<JobOfferEntity> jobOfferEntities;

    public UserEntity(String login, String name, LocalDate creationDate, String password) {
        this.login = login;
        this.name = name;
        this.creationDate = creationDate;
        this.password = password;
    }

    public UserOutput convertEntityToOutput() {
        return new UserOutput(id, login, name, creationDate);
    }

    public String GetName() {
        return name;
    }
}
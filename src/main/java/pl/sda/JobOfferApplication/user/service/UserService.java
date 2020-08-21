package pl.sda.JobOfferApplication.user.service;

import pl.sda.JobOfferApplication.user.exception.PasswordException;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;

public interface UserService {
    public void createUser(UserInput userInput) throws UserException, PasswordException;

    public List<UserOutput> getAllUsers();

    UserOutput getUserById(Long id) throws UserException;

}

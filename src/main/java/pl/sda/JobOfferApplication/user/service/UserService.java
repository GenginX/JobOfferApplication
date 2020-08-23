package pl.sda.JobOfferApplication.user.service;

import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;

public interface UserService {
    public void createUser(UserInput userInput) throws UserException;

    public List<UserOutput> getAllUsers();

    UserOutput getUserById(Long id) throws UserException;

    void deleteUserById(Long id) throws UserException;

    public UserEntity getUserEntity(Long id) throws UserException;

}

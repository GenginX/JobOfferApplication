package pl.sda.JobOfferApplication.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.JobOfferApplication.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.PasswordException;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    public static final String NO_USER_FOUND_FOR_GIVEN_ID = "No User found for given id";
    public static final String USER_WITH_THIS_LOGIN_ALREADY_EXISTS = "User with this login, already exists";
    public static final String PASSWORD_IS_WEAK = "Please provivide stronger password: length higher than 8, one big letter, one small letter, one digit, one special character";
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired


    @Override
    public List<UserOutput> getAllUsers() {
        return userRepository.findAll().
                stream()
//                .map(e -> new UserOutput(e.getId(), e.getLogin(), e.getName(), e.getCreationDate()))
                .map(e -> e.convertEntityToOutput())
                .collect(Collectors.toList());

    }

    @Override
    public void createUser(UserInput userInput) throws UserException {

//       Dodac validacje czy Login ma wiecej niz 6 znakow i czy haslo ma 1 znak specjalny, 1 wielka litera i 1 cyfre, dlugos hasla > 8 znakow
        userInputValidation(userInput);
        passwordValidation(userInput.getPassword());

        final String encode = passwordEncoder.encode(userInput.getPassword());

        UserEntity userEntity = new UserEntity(userInput.getLogin(),
                userInput.getName(),
                userInput.getCreationDate(),
                encode);

        userRepository.save(userEntity);
    }

    @Override
    public UserOutput getUserById(Long id) throws UserException {
        final Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if(!optionalUserEntity.isPresent()){
            throw new UserException(NO_USER_FOUND_FOR_GIVEN_ID);
        }
        return optionalUserEntity.get().convertEntityToOutput();
//                .stream()
//                .map(UserEntity::convertEntityToOutput)
//                .findAny()
//                .orElse(null);
    }

    private void userInputValidation(UserInput userInput) throws UserException{
        if(userRepository.existsByLogin(userInput.getLogin())){
            throw new UserException(USER_WITH_THIS_LOGIN_ALREADY_EXISTS);
        }
    }

    private void passwordValidation(String password) throws UserException {
        Pattern bigLetter = Pattern.compile("[A-Z]");
        Pattern smallLetter = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%^&*()_+\\[\\]{};',.:|<>?]");

        Matcher hasBigLetter = bigLetter.matcher(password);
        Matcher hasSmallLetter = smallLetter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        if(password.length() < 8 || !hasDigit.find() || !hasBigLetter.find() || !hasSmallLetter.find() || !hasSpecial.find()){
            throw new UserException(PASSWORD_IS_WEAK);
        }
    }
}

// Zrobic nowy projekt Spring Init i zrobic aplikacje od nowa

//Afasfasf
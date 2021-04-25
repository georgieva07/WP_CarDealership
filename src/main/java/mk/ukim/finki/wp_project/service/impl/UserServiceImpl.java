package mk.ukim.finki.wp_project.service.impl;

import mk.ukim.finki.wp_project.model.Country;
import mk.ukim.finki.wp_project.model.User;
import mk.ukim.finki.wp_project.model.exceptions.*;
import mk.ukim.finki.wp_project.repository.CountryRepository;
import mk.ukim.finki.wp_project.repository.UserRepository;
import mk.ukim.finki.wp_project.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUsernameCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String country){
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(!this.userRepository.findByUsername(username).isEmpty()){
            throw new UsernameAlreadyExistsException();
        }

        Country country1 = countryRepository.findByName(country).orElseThrow(InvalidCountryNameException::new);
        String encryptedPassword = this.passwordEncoder.encode(password);

        User user = new User(username, encryptedPassword, name, surname, country1);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(InvalidUsernameCredentialsException::new);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}

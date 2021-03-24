package mk.ukim.finki.wp_project.service;

import mk.ukim.finki.wp_project.model.User;
import mk.ukim.finki.wp_project.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, String country);
}

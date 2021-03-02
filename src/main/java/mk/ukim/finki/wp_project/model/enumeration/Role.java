package mk.ukim.finki.wp_project.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;
    @Override
    public String getAuthority(){return name();}
}

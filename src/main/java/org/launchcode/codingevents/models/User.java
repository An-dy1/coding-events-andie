package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String passwordHashField;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(String username, String password) {
        this.username = username;
        this.passwordHashField = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        if(encoder.matches(password, passwordHashField)) {
            return true;
        } else {
            return false;
        }
    }
}

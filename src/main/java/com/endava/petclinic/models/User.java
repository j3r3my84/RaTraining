package com.endava.petclinic.models;

import com.github.javafaker.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Boolean enabled;
    private String username;
    private String password;
    private List<Role> roles;

    public User() {
    }

    // new User("admin", "admin", "OWNER_ADMIN")
    // new User("admin", "admin", "OWNER_ADMIN", "VET_ADMIN")

    public User( String username, String password, String... roles) {
        this.enabled = true;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        for(String roleName : roles){
            Role role = new Role(roleName);
            this.roles.add(role);
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(enabled, user.enabled) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, username, password, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "enabled=" + enabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}

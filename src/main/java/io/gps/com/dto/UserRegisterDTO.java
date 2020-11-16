package io.gps.com.dto;

public class UserRegisterDTO {
    private String firstName;
    private String email;
    private String password;
    private String lastName;

    public UserRegisterDTO(String firstName, String email, String password, String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
    }

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

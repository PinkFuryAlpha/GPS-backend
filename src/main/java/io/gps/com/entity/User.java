package io.gps.com.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name must not be blank!")
    @NotNull
    @Column(name = "F_NAME", nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "Second name must not be blank!")
    @NotNull
    @Column(name = "S_NAME", nullable = false, length = 20)
    private String secondName;

    @NotBlank(message = "Email must not be blank!")
    @NotNull
    @Email
    @Column(name = "EMAIL", nullable = false, length = 45)
    private String email;

    @NotBlank(message = "Password must not be blank!")
    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 256)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    private Collection<Location> location;

    public User() {
    }

    public User(String firstName, String email, String password, String secondName, Collection<Role> roles) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.secondName = secondName;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

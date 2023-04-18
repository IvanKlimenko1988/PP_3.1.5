package ru.kata.spring.boot_security.demo.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "Поле имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть длиной от 2 до 30 символов")
    private String username;
    @Column(name = "surname")
    @NotEmpty(message = "Поле фамилия не должно быть пустым")
    @Size(min = 2, max = 30, message = "Фамилия должна быть длиной от 2 до 30 символов")
    private String surname;
    @Column(name = "age")
    @Min(value = 0, message = "Возраст не должен быть меньше нуля")
    private int age;
    @Column(name = "email")
    @NotEmpty(message = "Почта не должна быть пустой")
    @Email(message = "Почта должна быть корректна")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 4, max = 255, message = "Пароль должен быть от 4 до 10 символов")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, int age, String email, String password) {
        this.username = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(username, user.username) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, surname, age, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", roles=" + roles +
                '}';
    }
}

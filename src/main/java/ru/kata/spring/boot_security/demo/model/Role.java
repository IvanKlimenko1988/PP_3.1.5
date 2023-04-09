package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="authorities")
public class  Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    public Role() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}

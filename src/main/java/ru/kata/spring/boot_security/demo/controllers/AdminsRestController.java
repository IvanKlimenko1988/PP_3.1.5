package ru.kata.spring.boot_security.demo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminsRestController {

    private final UserService userService;

//    private final ModelMapper modelMapper;


    private final RoleService roleService;

    @Autowired
    public AdminsRestController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
//        this.modelMapper = modelMapper;
    }

//    @GetMapping("/users")
//    public List<UserDTO> getUsers() {
//        return userService.findAll().stream().map(this::convertToUserDTO)
//                .collect(Collectors.toList());
//    }
//    @GetMapping("/roles")
//    public List<Role> getRoles() {
//        return roleService.getAllRoles();
//    }
//
//    @GetMapping("/users/{id}")
//    public UserDTO getUserByID(@PathVariable("id") long id) {
//        return convertToUserDTO(userService.findById(id)) ;
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDTO,
//                                                 BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for(FieldError error : errors) {
//                errorMsg.append(error.getField())
//                        .append(" - ").append(error.getDefaultMessage())
//                        .append(";");
//            }
//            throw new UserNotFoundException(errorMsg.toString());
//        }
//        userService.addUser(convertToUser(userDTO));
//        return ResponseEntity.ok(HttpStatus.OK);
//    }


//    @PutMapping("/users")
//    public User updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return user;
//    }


    //    @DeleteMapping("/users/{id}")
//    public String deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "DELETE FINISHED";
//    }
/////////////////////////////////
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PatchMapping(value = "/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //таблица юзера после аунтификации
    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //роли
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}



//    private User convertToUser(UserDTO userDTO) {
//        return modelMapper.map(userDTO, User.class);
//    }

//    private UserDTO convertToUserDTO(User user) {
//        return modelMapper.map(user, UserDTO.class);
//    }
//
//}
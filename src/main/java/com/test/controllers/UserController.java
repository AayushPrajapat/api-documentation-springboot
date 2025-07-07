package com.test.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.entities.User;
import com.test.helper.ApiResponseMessage;
import com.test.services.UserService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Handles CRUD operations for users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    public ResponseEntity<User> createdUser(@Valid @RequestBody User user) {
        User createUser = this.userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user", description = "Updates an existing user by their ID.")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable("userId") int userId) {
        User updatedUser = this.userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") int userId) {
        this.userService.deleteUser(userId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setMessage("User is Successfully Deleted!!");
        apiResponseMessage.setSuccess(true);
        apiResponseMessage.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get a user by ID", description = "Retrieves a user by their ID.")
    public ResponseEntity<User> getById(@PathVariable("userId") int userId) {
        User user = this.userService.getById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get a user by email", description = "Retrieves a user by their email.")
    public ResponseEntity<User> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(this.userService.getByEmail(email), HttpStatus.OK);
    }
}


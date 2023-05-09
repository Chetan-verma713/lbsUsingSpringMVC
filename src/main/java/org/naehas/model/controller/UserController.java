package org.naehas.model.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.naehas.model.model.User;
import org.naehas.model.service.UserService;

import org.naehas.utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return new ResponseEntity<>("user inserted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("user not inserted!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("user deleted!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("user not deleted!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            System.out.println(user);
            userService.updateUser(user);
            return new ResponseEntity<>("user updated!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("user not updated!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getUsers() {
        try {
            List<User> users = userService.getUsers();
            System.out.println(users);
            return new ResponseEntity<>(new Parser<>().toJson(users), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public ResponseEntity<String> getUserById(@PathVariable long user_id) {
        try {
            User user = userService.getUserById(user_id);
            System.out.println(user);
            return new ResponseEntity<>(new Parser<>().toJson(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("something went wrong!", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/borrow")
    @ResponseBody
    public ResponseEntity<String> borrowBook(@PathVariable("id") long userId, @RequestParam long id) {
        try {
            userService.borrowBook(userId, id);
            return new ResponseEntity<>("book issued!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("book is not issued!", HttpStatus.BAD_REQUEST);
        }
    }

}

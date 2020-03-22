package com.example.pss.controller;

import com.example.pss.model.User;
import com.example.pss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public void register(@RequestParam("user") User user){
        userService.registerUser(user);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PutMapping("/change")
    public void changePassword(@RequestParam("id") Long id,
                               @RequestParam("pwd") String pwd){
        userService.changePassword(id, pwd);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public boolean deleteById(@RequestParam("id") Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/allByRole")
    @ResponseBody
    public List<User> getAllByRole(@RequestParam("name") String name){
        return userService.getAllUsersByRoleName(name);
    }
}

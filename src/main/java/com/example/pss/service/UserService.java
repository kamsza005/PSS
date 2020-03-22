package com.example.pss.service;

import com.example.pss.model.Delegation;
import com.example.pss.model.User;
import com.example.pss.repository.DelegationRep;
import com.example.pss.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRep userRepository;

    @Autowired
    DelegationRep delegationRepository;

    public void registerUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void changePassword(Long userId, String newPassword){
        Optional<User> user = userRepository.findById(userId);
        user.get().setPassword(newPassword);
    }

    public boolean deleteUserById(Long userId){
        List<Delegation> delegations = delegationRepository.findAllByUserId(userId);
        delegationRepository.deleteAll(delegations);

        userRepository.deleteById(userId);
    }

    public List<User> getAllUsersByRoleName(String roleName){
        return userRepository.findAll()
                .stream()
                    .filter(role -> role.getRoles()
                            .stream()
                            .anyMatch(name -> name.getRoleName().equals(roleName)))
                .collect(Collectors.toList());
    }
}

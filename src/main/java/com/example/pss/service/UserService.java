package com.example.pss.service;

import com.example.pss.repository.DelegationRep;
import com.example.pss.repository.RoleRep;
import com.example.pss.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.pss.model.User;

import java.util.List;

@Transactional
@Service
public class UserService {

    UserRep userRep;
    RoleRep roleRep;
    DelegationRep delegationRep;

    @Autowired
    public UserService(UserRep userRep, RoleRep roleRep, DelegationRep delegationRep) {
        this.userRep = userRep;
        this.roleRep = roleRep;
        this.delegationRep = delegationRep;
    }

    public List<User> getAllUsers() {
        return userRep.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public User getUser(long id) {
        return userRep.findById(id).get();
    }

    public User createUser(User user) {
        return userRep.save(user);
    }

    public void updateUser(User user) {
        userRep.save(user);
    }

    public void deleteUserById(long id) {
        roleRep.deleteUserInRole(id);
        delegationRep.deleteUserInDele(id);
        userRep.deleteById(id);

        /*

        List<Delegation> delegations = delegationRepository.findAllByUserId(userId);
        delegationRepository.deleteAll(delegations);

        userRepository.deleteById(userId);

         */
    }

    public void changePassword(long id, String password) {
        User user = userRep.findById(id).get();
        user.setPassword(password);
        userRep.save(user);
    }
}

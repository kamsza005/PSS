package com.example.pss.service;

import com.example.pss.model.Delegation;
import com.example.pss.model.User;
import com.example.pss.repository.DelegationRep;
import com.example.pss.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DelegationService {

    @Autowired
    UserRep userRepository;

    @Autowired
    DelegationRep delegationRepository;

    public void addDelegation(Long userId, Delegation delegation){
        Optional<User> user = userRepository.findById(userId);
        delegation.setUser(userId);
        delegationRepository.save(delegation);
    }

    public boolean removeDelegation(Long userId, Long delegationId){
        Optional<Delegation> delegation = delegationRepository.findById(delegationId);

        if(delegation.isPresent()) {
            Delegation thisDelegation = delegation.get();

            if (thisDelegation.getUser().getId().equals(userId)) {
                delegationRepository.delete(thisDelegation);
                return true;
            }
        }
        return false;
    }

    public void changeDelegation(Long delegationId, Delegation delegation){
        Optional<Delegation> delegation2 = delegationRepository.findById(delegationId);

        //...

    }

    public List<Delegation> getAllDelegations(){
        return delegationRepository.findAll();
    }

    public List<Delegation> getAllDelegationsOrderByDateTimeStartDesc(){
        return delegationRepository.findByOrderByDateTimeStartDesc();
    }

    public List<Delegation> getAllDelByUserByDateTimeStartDesc(Long userId){
        return delegationRepository.findAllByUserIdOrderByDateTimeStartDesc(userId);
    }
}

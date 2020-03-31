package com.example.pss.service;

import com.example.pss.model.*;
import com.example.pss.repository.DelegationRep;
import com.example.pss.repository.RoleRep;
import com.example.pss.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class InitService {

    UserRep userRepository;
    RoleRep roleRepository;
    DelegationRep delegationRepository;

    @Autowired
    public InitService(UserRep userRepository, RoleRep roleRepository,
                       DelegationRep delegationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.delegationRepository = delegationRepository;

    }

    @PostConstruct
    public void init() {
        roleRepository.deleteAllInBatch();
        delegationRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        Role r1 = new Role("Role1");
        Role r2 = new Role("Role2");
        Role r3 = new Role("Role3");

        Delegation d1 = new Delegation("Delegacja nr 1", LocalDate.now(), LocalDate.now().plusDays(5),
                50, 1, 1, 2, TransportEnum.auto, 0,
                AutoCapacityEnum.ponad_900, 100.00, 100, 50, 50);
        Delegation d2 = new Delegation("Delegacja nr 2", LocalDate.now(), LocalDate.now().plusDays(10),
                60, 2, 1, 0, TransportEnum.bus, 0,
                AutoCapacityEnum.mniej_row_900, 200.00, 100, 50, 50);
        Delegation d3 = new Delegation("Delegacja nr 3", LocalDate.now(), LocalDate.now().plusDays(15),
                50, 1, 1, 1, TransportEnum.pociag, 34.50,
                AutoCapacityEnum.none, 0.0, 100, 50, 50);

        User u1 = new User("abc", "Bydgoszcz", "nip1",
                "Jan", "Kowalski", "kowalski@gmail.com", "abc123");
        User u2 = new User("def", "Warszawa", "nip2",
                "Jerzy", "Nowak", "nowak@wp.pl", "def456");


        r1.addUser(u1);
        r2.addUser(u1);
        r2.addUser(u2);
        r3.addUser(u2);

        d1.addUser(u1);
        d2.addUser(u1);
        d3.addUser(u2);

        userRepository.saveAll(Arrays.asList(u1, u2));
        roleRepository.saveAll(Arrays.asList(r1, r2, r3));
        delegationRepository.saveAll(Arrays.asList(d1, d2));

        userRepository.findAll().forEach(user -> {
            System.out.println(user);
            System.out.println(user.getRoles());
            System.out.println(user.getDelegations());
        });

    }
}

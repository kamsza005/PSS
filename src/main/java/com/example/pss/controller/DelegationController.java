package com.example.pss.controller;

import com.example.pss.model.Delegation;
import com.example.pss.service.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delegation")
public class DelegationController {

    @Autowired
    DelegationService delegationService;

    @PostMapping("/add")
    public void add(@RequestParam("id") Long id,
                    @RequestParam("delegation") Delegation delegation){
        delegationService.addDelegation(id, delegation);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public boolean remove(@RequestParam("userId") Long userId,
                          @RequestParam("delegationId") Long delegationId){
        return delegationService.removeDelegation(userId, delegationId);
    }

    @PutMapping("/change")
    public void change(@RequestParam("id") Long id,
                                 @RequestParam("delegation") Delegation delegation){
        delegationService.changeDelegation(id, delegation);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Delegation> getAll(){
        return delegationService.getAllDelegations();
    }

    @GetMapping("/allInOrder")
    @ResponseBody
    public List<Delegation> getAllInOrder(){
        return delegationService.getAllDelegationsOrderByDateTimeStartDesc();
    }

    @GetMapping("allByUser")
    @ResponseBody
    public List<Delegation> getAllByUserInOrder(@RequestParam("id") Long id){
        return delegationService.getAllDelByUserByDateTimeStartDesc(id);
    }
}

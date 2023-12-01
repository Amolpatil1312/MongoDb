package com.csi.service;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public Customer signUp(Customer customer){
        return customerRepo.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

    public boolean signIn(String custEmail,String custPassword){
        boolean flag = false;
        for(Customer customer : findAll()){
            if(customer.getCustEmailId().equals(custEmail) && customer.getCustPassword().equals(custPassword)){
                flag = true;
            }
        }
        return flag;
    }

    public Optional<Customer> findById(int custId){
        return customerRepo.findById(custId);
    }

    public List<Customer> findByName(String custName){
        return findAll().stream().filter(customer -> customer.getCustName().equals(custName)).toList();
    }

    public Customer findByContactNumber(long custContact){
        return findAll().stream().filter(customer -> customer.getCustContactNumber()==custContact).collect(Collectors.toList()).get(0);
    }

    public Customer findByEmailId(String custEmailId){
        return findAll().stream().filter(customer -> customer.getCustEmailId().equals(custEmailId)).collect(Collectors.toList()).get(0);
    }

    public List<Customer> sortById(){
        return findAll().stream().sorted(Comparator.comparingInt(Customer::getCustId)).toList();
    }

    public List<Customer> sortByName(){
        return findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList();
    }

    public List<Customer> sortByAccountBalance(){
        return findAll().stream().sorted(Comparator.comparingDouble(Customer::getCustActBalence)).toList();
    }

    public Customer updateData(Customer customer){
        return customerRepo.save(customer);
    }

    public List<Customer> filterByActBalance(double custActBalance){
        return findAll().stream().filter(customer -> customer.getCustActBalence()>=custActBalance).toList();
    }

    public void deleteById(int custId){
        customerRepo.deleteById(custId);
    }

    public void deleteAll(){
        customerRepo.deleteAll();
    }
}

package com.csi.repo;

import com.csi.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer,Integer> {
    //custom methods goes here
}

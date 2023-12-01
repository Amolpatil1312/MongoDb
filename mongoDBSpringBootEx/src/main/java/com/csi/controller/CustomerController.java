package com.csi.controller;

import com.csi.exception.RecordNotFoundExeption;
import com.csi.model.Customer;
import com.csi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.signUp(customer));
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword){
        return ResponseEntity.ok(customerService.signIn(custEmailId, custPassword));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@Valid @PathVariable int custId){
        return ResponseEntity.ok(Optional.ofNullable(customerService.findById(custId).orElseThrow(() -> new RecordNotFoundExeption("This Record Not Exist"))));
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName){
        return ResponseEntity.ok(customerService.findByName(custName));
    }

    @GetMapping("/findbycontact/{custContact}")
    public ResponseEntity<Customer> findByContactNumber(@PathVariable long custContact){
        return ResponseEntity.ok(customerService.findByContactNumber(custContact));
    }

    @GetMapping("/findbyemail/{custEmail}")
    public ResponseEntity<Customer> findByEmail(@PathVariable String custEmail){
        return ResponseEntity.ok(customerService.findByEmailId(custEmail));
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Customer>> sortById(){
        return ResponseEntity.ok(customerService.sortById());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer> > sortByName(){
        return ResponseEntity.ok(customerService.sortByName());
    }

    @GetMapping("/sorbyactbalance")
    public ResponseEntity<List<Customer>> sortByActBalance(){
        return ResponseEntity.ok(customerService.sortByAccountBalance());
    }

    @PutMapping("/update/{custId}")
    public Customer updateData(@Valid @RequestBody Customer customer, @PathVariable int custId){
        Customer customer1 = findById(custId).getBody().orElseThrow(()->new RecordNotFoundExeption("This Record is Not Exist into Database"));

        customer1.setCustUID(customer.getCustUID());
        customer1.setCustPassword(customer.getCustPassword());
        customer1.setCustPancard(customer.getCustPancard());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustName(customer.getCustName());
        return customerService.updateData(customer1);
    }

    @GetMapping("/filterbyactbalance/{custactbalance}")
    public List<Customer> filterByActBalance(@PathVariable int custactbalance ){
        return customerService.filterByActBalance(custactbalance);
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable int custId){
        Customer customer = findById(custId).getBody().orElseThrow(()->new RecordNotFoundExeption("ID Not Exist"));
        customerService.deleteById(custId);
        return ResponseEntity.ok("All data deleted successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        customerService.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }

}

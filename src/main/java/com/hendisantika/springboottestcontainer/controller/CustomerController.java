package com.hendisantika.springboottestcontainer.controller;

import com.hendisantika.springboottestcontainer.entity.Customer;
import com.hendisantika.springboottestcontainer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/6/23
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;


    @GetMapping("/api/customers")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}

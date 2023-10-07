package com.alejo.appsecurity.repositories;

import com.alejo.appsecurity.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, BigInteger> {
    /*Implementamos un método para cargar usuario por Email*/
    Optional<Customer> findByEmail(String email);
}

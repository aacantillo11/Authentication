package com.alejo.appsecurity.service;

import com.alejo.appsecurity.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*Debemos crear nuestra propia implementación de UserDetailsService para lo cual
* debemos implementar la interface UserDetailsService con el método loadUserByUsername*/
@Service
@Transactional
public class CustomerUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    /*Para probar esta implementación tenemos que ir a la configuración de seguridad y comentar nuestro UserDetailsService
    * Como esta clase la hemos anotado con @Service spring va identificar que este es el USerDetails que debe implementar*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username).map(
                customer -> {
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
                    return new User(customer.getEmail(), customer.getPassword(), authorities);
                }
        ).orElseThrow( () -> new UsernameNotFoundException("User not found"));
    }
}

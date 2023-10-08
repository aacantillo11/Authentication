package com.alejo.appsecurity.security;

import com.alejo.appsecurity.entities.Customer;
import com.alejo.appsecurity.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder  passwordEncoder;

    /*Lógica de autenticación personalizada aquí.
      Puedes implementar tu propia lógica para autenticar al usuario.
       Si la autenticación es exitosa, debes devolver una instancia de Authentication con los detalles del usuario autenticado.
       Si la autenticación falla, puedes lanzar una excepción AuthenticationException.
       Por ejemplo, BadCredentialsException si las credenciales son incorrectas.*/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final Optional<Customer> customerFromDB = customerRepository.findByEmail(username);
        final Customer customer = customerFromDB.orElseThrow(()->new BadCredentialsException("Invalid credentials"));
        final String customerPassword = customer.getPassword();

        if(!passwordEncoder.matches(password,customerPassword)){
            throw new BadCredentialsException("Invalid credentials");
        }

        final List<SimpleGrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(customer.getRole()));

        return new UsernamePasswordAuthenticationToken(username,password,authorities);
    }


    /*Este método determina si esta clase de autenticación es compatible con la que se proporciona.
      En este caso, verifica si la autenticación es de tipo UsernamePasswordAuthenticationToken. */
    @Override
    public boolean supports(Class<?> authentication) {
        //Retornamos la autenticación que va a soportar
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

package com.alejo.appsecurity.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
* Para realizar nuestra propia immplementación de Password Encoder debemos implementar la interface
* PasswordEncoder e implementar los métodos encode y matches*/
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword.toString().hashCode());//Estamos conviriendo a String rawPassword y obteniendo su hashCode
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String passwordAsString = String.valueOf(rawPassword.toString().hashCode()); //Obetenrmos el String del hashCode para comprarlo con el password codificado
        return encodedPassword.equals(passwordAsString);
    }
}

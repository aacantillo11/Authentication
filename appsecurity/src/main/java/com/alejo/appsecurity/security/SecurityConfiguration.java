package com.alejo.appsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /*Creamos un bean para manejar nuestros filtros de seguridad.
    * Configuramos nuestros filtros para que cualquier solicitud tenga que autenticarse por medio de un formulario
    * con una autenticación básica.
    * http.authorizeHttpRequests().anyRequest().authenticated() le indicamos que cualquier request http debe estar
    * autenticado.
    * http.authorizeHttpRequests().anyRequest().permitAll() -> En este caso permitimos que cualquier solicitud http
    * acceda a nuestros endpoints
    * Pero si queremos proteger ciertos endpoints y otros no lo podemos realizar de la siguiente manera:
    * http.authorizeHttpRequests().antMatchers("/loans","/balance","/cards","/accounts")
                .authenticated().anyRequest().permitAll()
                .and().formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()).build();
    **/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests().antMatchers("/loans","/balance","/cards","/accounts")
                .authenticated().anyRequest().permitAll()
                .and().formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()).build();
    }
}

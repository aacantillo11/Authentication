package com.alejo.appsecurity.security;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    //Para crear usuarios en memoria
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        //Creamos nuestros usuarios
        UserDetails admin = User.withUsername("admin") //Definimos el username
                .password("to_be_encoded")             //Definimos el password
                .authorities("ADMIN")                  //Definimos la autorización
                .build();

        UserDetails user = User.withUsername("user")
                .password("to_be_encoded")
                .authorities("USER")
                .build();
        //Retornamos nuestro usuario pasandolos al InMemoryUserDetailsManager
        return new InMemoryUserDetailsManager(admin,user);
    }

    /*Cuando configuramos nuestro ususarios en memoria y queremos realizar una petición con nuestros user y password
    * Vamos a presentar el siguiente error:
    * There was an unexpected error (type=Internal Server Error, status=500).
    * There is no PasswordEncoder mapped for the id "null"
    * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
    * Debido a que no hemos definido un bean para el PasswordEncoder y encriptar las contraseñas*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();//Lo utilizamos para indicar que no vamos a encriptar nuestra contraseña pero solo para caso de prueba cuando estamos desarrolladno nuestra seguridad desde cero
    }

}

// Paquetes
package co.edu.udec.libraryproject.security;

// Librerías
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Web security configuration
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/11/2021
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    // Variables

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Constructor
     */
    public WebSecurityConfiguration() {

    }

    // Métodos

    /**
     * Establecer el authentication provider
     * @return Authentication provider cargado
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;

    }

    /**
     * Establecer permisos de autenticación
     */
    @Override
    protected void configure(HttpSecurity http) throws   Exception {
        http
            .authorizeRequests()
            .antMatchers("/usuarios/**").permitAll()
            .antMatchers("/administradores/**").hasAuthority("Administrador")
            .antMatchers("/bibliotecarios/**").hasAuthority("Bibliotecario")
            .antMatchers("/autores/**").hasAuthority("Autor")
            .and()
            .httpBasic();

    }    

}

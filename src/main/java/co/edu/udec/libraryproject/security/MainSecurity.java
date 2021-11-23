// Paquete
package co.edu.udec.libraryproject.security;

// Librerías
import co.edu.udec.libraryproject.filter.FJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.edu.udec.libraryproject.security.jwt.JWTEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * Web security configurer
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 22/11/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {
    
    // Variables

    /**
     * Métodos de la capa de servicios de user details
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Control de respuesta de JWT
     */
    @Autowired
    private JWTEntryPoint jwtEntryPoint;

    // Métodos

    /**
     * Preparando el filtro de Java Web Tokens
     * @return Filtro preparado
     */
    @Bean
    public FJWT jwtTokenFilter() {
        return new FJWT();
    }

    /**
     * Preparando password encoder
     * @return Password encoder preparado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {        
        return super.authenticationManager();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {        
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {        
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configuración de la seguridad del API
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Configuración
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/autores/**").authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Filtro de JWT
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }     

}

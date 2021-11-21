// Paquete
package co.edu.udec.proyecto.security;

// Librerías
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.udec.proyecto.exception.AuthenticationJWTException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceService extends ResourceServerConfigurerAdapter {
    
    // Variables

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    /**
     * Constructor
     */
    public ResourceService() {

    }

    // Métodos

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws    Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .exceptionHandling().authenticationEntryPoint(new AuthenticationJWTException())
            .and()
            .requestMatchers()
            .and()
            .authorizeRequests()
            .antMatchers("/autores/**").authenticated()
            .antMatchers("/libros/**").authenticated()
            .antMatchers("/editorial/**").authenticated(); 
    }

}

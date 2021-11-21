// Paquete
package co.edu.udec.proyecto.security;

// Librerías
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * Clase de configuración de seguridad
 * @author Jhonattan Pulido
 * @since 20/11/2021
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // Variables

    /**
     * Clave de seguridad de JWT
     */
    @Value("${security.signing-key}")
    private String signingKey;

    /**
     * Tipo de algoritmo
     */
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    /**
     * Alias
     */
    @Value("${security.security-realm}")
    private String securityRealm;

    /**
     * Métodos de codificar clave
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Métodos para obtener los usuarios que inician sesión
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Métodos para obtener tokens almacenados en BD
     */
    @Autowired
    private DataSource dataSource;

    /**
     * Constructor
     */
    public SecurityConfig() {

    }

    // Métodos

    /**
     * Generar objeto de codificador
     * @return objeto de codificador
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws  Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws   Exception {
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * Establecer comportamiento de Spring Security
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic()
            .realmName(securityRealm)
            .and()
            .csrf()
            .disable();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtConverter = new JwtAccessTokenConverter();
        jwtConverter.setSigningKey(signingKey);
        return jwtConverter;
    }

    /**
     * Se especifica donde se almacenarán los JWT
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(this.dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore((tokenStore()));
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        return defaultTokenServices;
    }

}

package brcom.wernen.gerenciadortarefas.configurations

import brcom.wernen.gerenciadortarefas.filters.JWTFilterAuthorization
import brcom.wernen.gerenciadortarefas.utils.JWRUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtUtils: JWRUtils

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
            .anyRequest().authenticated()

        http.addFilter(JWTFilterAuthorization(authenticationManager(), jwtUtils))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}
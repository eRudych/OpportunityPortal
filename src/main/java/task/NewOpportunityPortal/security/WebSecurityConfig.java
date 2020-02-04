package task.NewOpportunityPortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    CustomAuthenticationProvider customAuthProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/welcome","/adverts","/users/**","/comments/**").permitAll()
                .antMatchers("/chats","/messages").authenticated()
                .antMatchers("/getEmployees").hasAnyRole( "ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().logout().logoutSuccessUrl("/welcome").deleteCookies("JSESSIONID")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

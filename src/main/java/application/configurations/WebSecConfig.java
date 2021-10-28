package application.configurations;

import application.models.UserAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*.antMatchers("/**")
                .permitAll()
                .anyRequest().authenticated()
              .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/welcomeUser")
                .failureForwardUrl("/login")
              .and()
                .logout().logoutSuccessUrl("/login")*/

                // következő: bejelentkezett felhasználó ÉS READ joga van
                .antMatchers("/game").hasAuthority("ADD")             // következő: bejelentkezett, tök mindegy, milyen joggal
                .antMatchers("/gameForUser").hasAnyAuthority("READ", "ADD")
                .antMatchers("/welcomeUser").authenticated()
                // következő: bárki (nem kell bejelentkezés sem)
                .anyRequest().permitAll()
        ;
    }
}

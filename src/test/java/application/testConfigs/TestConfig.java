package application.testConfigs;

import application.models.User;
import application.models.UserRole;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        List<UserDetails> list = new ArrayList<>();
        list.add(new User("user", "user", 1, UserRole.USER, "READ"));
        list.add(new User("admin", "admin", 1, UserRole.ADMIN, "ADD"));

        return new InMemoryUserDetailsManager(list);
    }

}

package nghiavt.hustp2samiprojectapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .anyRequest().permitAll()
//                .requestMatchers("/sv-homeinfo").hasAuthority("STUDENT")
//                .requestMatchers("/sv-apply").hasAuthority("TEACHER")
                .and().formLogin()
                .successHandler(((request, response, authentication) -> {
                    String targetUrl ="/login";
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    for (GrantedAuthority authority : authorities){
                        if (authority.getAuthority().equals("STUDENT")){
                            targetUrl = "/sv-homeinfo";
                            break;
                        }
                    }
                    response.sendRedirect(targetUrl);
                }))
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

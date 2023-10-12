package project.maybedo.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean  // IoC가 된다
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()  // request가 들어오면
                .antMatchers("/")  // 이와 같은 주소로 들어오면
                .permitAll()
                .anyRequest()  // 다른 모든 요청은
                .authenticated()  // 인증이 되어야함, 인증이 되지 않은 요청은 무조건 로그인 폼으로 이동
                .and()
                .formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/login")  // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해줌.
                .defaultSuccessUrl("/");   // 로그인이 성공하면 해당 url로 넘어감

        return http.build();
    }
}

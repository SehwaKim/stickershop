package com.boot.stickershop.config;

import com.boot.stickershop.security.oauth2.AlreadyLoginCheckFilter;
import com.boot.stickershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;


@Configuration
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    ApplicationContext context;

    @Autowired
    UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/**.html"))
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().ignoringAntMatchers("/**")
//                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().disable()
                .and().formLogin()
                .loginProcessingUrl("/users/login")
                .loginPage("/users/login").usernameParameter("email").passwordParameter("password")
                /*.successHandler(customAuthenticationSuccessHandler())
                .failureHandler(customAuthenticationFailureHandler())*/
//                .and().rememberMe().tokenRepository(simpleBoardTokenRepositoryImpl).rememberMeParameter("remember-me").tokenValiditySeconds(1209600)
                .and().logout().permitAll();
                //.and()
                //    .addFilterBefore(new AlreadyLoginCheckFilter(), BasicAuthenticationFilter.class)
                //    .addFilterBefore((Filter) context.getBean("sso.filter"),BasicAuthenticationFilter.class);


    }

}
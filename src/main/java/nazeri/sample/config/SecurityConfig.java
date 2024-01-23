package nazeri.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import nazeri.sample.jwt.JwtTokenFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = false,securedEnabled = true)
public class SecurityConfig {
	
	@Autowired private JwtTokenFilter jwtTokenFilter;
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new CustomUserDetailsService() ;
	    }
	 
	   @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
	 
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	
	    	/*http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests()
				.antMatchers("/auth/login", "/docs/**", "/users").permitAll()
				.anyRequest().authenticated();
		
        http.exceptionHandling()
                .authenticationEntryPoint(
                    (request, response, ex) -> {
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                        );
                    }
                );
        
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);*/
	    	
	    	
	    	http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests.requestMatchers("/index","/","/login","/register","/process_register","/auth/signin","/api/v1/roles","/api/v1/roles/*").permitAll().anyRequest().authenticated())
	    		//.formLogin((formLogin)->formLogin.usernameParameter("email")/*.defaultSuccessUrl("/users")*/.permitAll())
	    	.formLogin((formLogin)->formLogin.permitAll())
	    		.logout((logout)->logout.logoutSuccessUrl("/").permitAll())
	    		
	    		.csrf((csrf)->csrf.disable())
	    		.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	    		.exceptionHandling((exceptionHandling)->exceptionHandling.authenticationEntryPoint((request, response, ex) -> {
                        response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                        );
                    }))
	    		.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
	    		;
	    	
	    	return http.build();
	    }
	 
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**","/css/**", "/webjars/**");
	    }

}
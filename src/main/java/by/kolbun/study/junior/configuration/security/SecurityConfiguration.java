package by.kolbun.study.junior.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Autowired
	private JWTTokenComponent jwtTokenComponent;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception { //formatter off

		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
						.antMatchers(HttpMethod.GET, "/").permitAll()
						.antMatchers(HttpMethod.GET, "*.css","*.js","*.png").permitAll()
						.antMatchers(HttpMethod.POST, "/auth").permitAll()
						.antMatchers(HttpMethod.GET, "/index").permitAll()
						.antMatchers(HttpMethod.GET, "/un").permitAll()
						.antMatchers(HttpMethod.GET, "/un/**").permitAll()
						.antMatchers(HttpMethod.POST, "/index").authenticated()
						.antMatchers(HttpMethod.GET, "/contentdata").authenticated()
					.anyRequest().anonymous()
				.and()
					.exceptionHandling().authenticationEntryPoint(authEntryPoint)
				.and()
					.addFilterBefore(new TokenAuthenticationFilter(jwtTokenComponent, userDetailsService), UsernamePasswordAuthenticationFilter.class);
	}//formatter on

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("*.css", "*.png", "*.js");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new JuniorPasswordEncoder();
	}

}

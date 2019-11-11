package oma_projekti.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//course avoin kaikille, ei vaadi kirjautumista
		//homework vain x-homeworkin luojalle
		//admin
			//voi muokata, lisätä ja poistaa courseja
			//voi nähdä kaikki kaikkien homeworkit, mutta ei muokata, poistaa yms niitä
		
		http
	    .authorizeRequests().antMatchers("/css/**", "/", "/courses", "/h2-console/**").permitAll()
	    .and().csrf().ignoringAntMatchers("/h2-console/**")
	    .and().headers().frameOptions().sameOrigin()
	    .and()
	    .authorizeRequests()
	    	.anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/welcome")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
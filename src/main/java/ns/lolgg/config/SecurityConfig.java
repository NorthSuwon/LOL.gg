//package ns.lolgg.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import ns.lolgg.service.UserService;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//			//접근 허용
//			.antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/h2-console/**", "/webjars/**", "/signup", "/index", "/")
//			.permitAll()
//			.anyRequest().authenticated()
//			.and()
//			//iframe 허용
//			.headers()
//			.frameOptions().disable()
//			.and()
//			//DB 어드민 접속 허용
//			.csrf()
//			.ignoringAntMatchers("/h2-console/**")
//			.and()
//			//로그인
//			.formLogin()
//			.loginPage("/login")
//			.loginProcessingUrl("/signin")
//			.defaultSuccessUrl("index")
//			.failureUrl("/index")
//			.usernameParameter("userId")
//			.passwordParameter("password")
//			.permitAll();
//			.and()
//		
//		
//		
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(null)
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder BCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public UserService userService() throws Exception {
//		return new UserService();
//	}
//	
//	
//}

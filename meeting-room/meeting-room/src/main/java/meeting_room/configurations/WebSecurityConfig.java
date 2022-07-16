//package meeting_room.configurations;
//
//import lombok.RequiredArgsConstructor;
//import meeting_room.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserServiceImpl userServiceImpl;
//	@Autowired
//	private DataSource dataSource;
//
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		return new JdbcUserDetailsManager(this.dataSource);
//	}
//
//
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
//	}
//}

package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.SimpleAuthentication;
import com.ufc.br.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImplementacao userDetailsServiceImple;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImple).passwordEncoder(new BCryptPasswordEncoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/img/**","/js/**","/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/rest/formulario").hasRole("GERENTE")
		.antMatchers("/rest/salvar").hasRole("GERENTE")
		.antMatchers("/rest/listar").hasRole("GERENTE")
		.antMatchers("/rest/exlcluir/**").hasRole("GERENTE")
		.antMatchers("/rest/atualizar/**").hasRole("GERENTE")
		.antMatchers("/pedido/add/**").hasRole("CLIENTE")
		.antMatchers("/pedido/deletar/**").hasRole("CLIENTE")
		.antMatchers("/pedido/pedidoAtual").hasRole("CLIENTE")
		.antMatchers("/pedido/finalizarPedido").hasRole("CLIENTE")
		.antMatchers("/pedido/pedidosEncerrados").hasRole("CLIENTE")	
		.antMatchers("/rest/cardapio").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/client/cadastrar").permitAll()
		.antMatchers("/gerente/cadastro").permitAll()
		.antMatchers("/gerente/del").permitAll()
		
		.antMatchers("/login").permitAll()
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
        .usernameParameter("email")
        .passwordParameter("senha")
        .successHandler(authenticationHandler)
		

        
        .and()
		.logout()
		.logoutSuccessUrl("/login")
		.permitAll();
	}
	
	@Autowired
	SimpleAuthentication authenticationHandler;
		
}
	
	
	
	
	


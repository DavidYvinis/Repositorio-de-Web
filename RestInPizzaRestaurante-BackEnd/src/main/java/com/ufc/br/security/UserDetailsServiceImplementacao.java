package com.ufc.br.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Pessoa;
import com.ufc.br.repository.PessoaRepository;

@Repository
public class UserDetailsServiceImplementacao implements UserDetailsService {
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa = pessoaRepo.findByEmail(email);
		if(pessoa == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return new User(pessoa.getUsername(), pessoa.getPassword(), true, true, true, true, pessoa.getAuthorities());
		
	}
	
}
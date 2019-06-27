package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Role;
import com.ufc.br.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepo;
	
	public void cadastrar(Role role) {
		roleRepo.save(role);
	}
		
	public Role pegarRole(String roleCodigo) {
		return roleRepo.getOne(roleCodigo);
	}

}

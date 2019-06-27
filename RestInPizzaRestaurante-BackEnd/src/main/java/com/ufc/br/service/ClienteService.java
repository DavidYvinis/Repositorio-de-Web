package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.ClienteRepository;
import com.ufc.br.service.RoleService;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	RoleService roleService;
	
	public void cadastrar(Cliente cliente) {
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		Role roleCliente = roleService.pegarRole("ROLE_CLIENTE");
		cliente.addRole(roleCliente);
		clienteRepo.save(cliente);
	}
	
	public Cliente obterCliente(String email) {
		return clienteRepo.findByEmail(email);
	}
}

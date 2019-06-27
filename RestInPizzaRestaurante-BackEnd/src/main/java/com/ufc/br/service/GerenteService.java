package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Gerente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.GerenteRepository;
import com.ufc.br.service.RoleService;

@Service
public class GerenteService {
	@Autowired
	GerenteRepository GerenteRepo;
	
	@Autowired
	RoleService roleService;
	
	public void cadastrar(Gerente gerente) {
		gerente.setSenha(new BCryptPasswordEncoder().encode(gerente.getSenha()));
		Role roleGerente = roleService.pegarRole("ROLE_GERENTE");
		gerente.addRole(roleGerente);
		GerenteRepo.save(gerente);
	}
	
	public List<Gerente> retornarGerentes(){
		return GerenteRepo.findAll();
	}

	public void excluir(Long id) {
		GerenteRepo.deleteById(id);
	}
	
	public Gerente buscarPorId(Long codigo) {
		return GerenteRepo.getOne(codigo);
	}
	
}

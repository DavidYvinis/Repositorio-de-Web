package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufc.br.model.Pessoa;
import com.ufc.br.repository.PessoaRepository;

public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepo;
	
	public void cadastrar(Pessoa pessoa) {
		pessoaRepo.save(pessoa);
	}
	
	public List<Pessoa> retornarUsuarios(){
		return pessoaRepo.findAll();
	}

	public void excluir(Long codigo) {
		pessoaRepo.deleteById(codigo);
	}
	
	public Pessoa buscarPorCodigo(Long codigo) {
		return pessoaRepo.getOne(codigo);
	}
}

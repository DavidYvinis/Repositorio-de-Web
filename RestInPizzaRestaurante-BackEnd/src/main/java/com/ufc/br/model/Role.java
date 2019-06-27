package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String nameRole;
	
	@ManyToMany
	private List<Pessoa> pessoas;
	
	@Override
	public String getAuthority() {
		return this.nameRole;
	}
	
	public Role() {
		this.pessoas = new ArrayList<Pessoa>();
	}
	
	public Role(String nameRole) {
		this.nameRole = nameRole;
		this.pessoas = new ArrayList<Pessoa>();
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public void addPessoa(Pessoa pessoa) {
		this.pessoas.add(pessoa);
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	

}

package com.ufc.br.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comida {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@NotBlank(message = "Preencha o campo do prato.")
	private String prato;
	
	@NotNull(message = "Preencha o campo do preço.")
	private Float preco;
	
	
	@NotNull(message = "O campo de data não pode ser nulo")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getPrato() {
		return prato;
	}
	public void setPrato(String prato) {
		this.prato = prato;
	}
	public Float getPreco() {
		return preco;
	}
	public void setpreco(Float preco) {
		this.preco = preco;
	}
	
	
	
	public void Produto(Long codigo, String prato, Float preco, Date dataNascimento) {
		this.codigo = codigo;
		this.prato = prato;
		this.preco = preco;
		this.dataNascimento = dataNascimento;
	}
	
	

}

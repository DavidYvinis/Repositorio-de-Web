package com.ufc.br.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ufc.br.model.Item;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	private float total;
	private String endereco;
	private String status;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToMany(targetEntity = Comida.class)
	private Set<Comida> prato;
	
	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
	private Set<Item> itens = new HashSet<Item>();
	
	
	public Pedido() {
		itens = new HashSet<Item>();
		total = 0F;
		status = "ativo";
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public float getTotal() {
		for (Item item : itens) {
			total += item.getQtd() * item.getPrato().getPreco();
		}
		return total;
	}
	
	public float getTotalFinal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public void attTotal() {
		for (Item item : itens) {
			total += item.getQtd() * item.getPrato().getPreco();
		}
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Set<Comida> getPrato() {
		return prato;
	}


	public void setPrato(Set<Comida> prato) {
		this.prato = prato;
	}


	public Set<Item> getItens() {
		return itens;
	}


	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	
	public void addItem(Item item) {
		this.itens.add(item);
	}
	
	
	
	
	
	
	
	
	
}

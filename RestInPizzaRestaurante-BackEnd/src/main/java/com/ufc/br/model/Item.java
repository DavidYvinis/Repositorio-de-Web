package com.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private int qtd;

	@OneToOne
	private Comida prato;
	
	@ManyToOne(targetEntity = Pedido.class, fetch = FetchType.EAGER)
	private Pedido pedido;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	

	public Comida getPrato() {
		return prato;
	}

	public void setPrato(Comida prato) {
		this.prato = prato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Item() {
	}

	public Item(Comida prato) {
		this.prato = prato;
		this.qtd = 1;
	}


}

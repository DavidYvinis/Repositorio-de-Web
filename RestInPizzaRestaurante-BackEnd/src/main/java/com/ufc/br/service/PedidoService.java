package com.ufc.br.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Comida;
import com.ufc.br.model.Item;
import com.ufc.br.model.Pedido;
import com.ufc.br.repository.PedidoRepository;
import com.ufc.br.service.ItemService;
import com.ufc.br.service.ComidaService;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository PedidoRepo;
	@Autowired
	ComidaService comidaService;
	@Autowired 
	ItemService itemService;
	
	public void cadastrarPedido(Pedido pedido) {
		PedidoRepo.save(pedido);
	}
	
	public void cadastrarPedido(Long codigo, Cliente cliente) {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		PedidoRepo.save(pedido);
		
	
	Comida prato = comidaService.buscarPrato(codigo);
	Item item = new Item(prato);
	item.setPedido(pedido);
	itemService.cadastrarItem(item);
	}
	
	public void deletarItem(Long codigo, Cliente cliente) {
		Pedido pedido = PedidoRepo.findByStatusAndCliente("ativo", cliente);
		Set<Item> itens =  pedido.getItens();
		for (Item item : itens) {
			if(item.getCodigo() == codigo) {
				itens.remove(item);
				break;
			}
		}
		pedido.setItens(itens);
		itemService.deletarItem(codigo);
		PedidoRepo.save(pedido);
	}

	public void finalizarPedido(Pedido pedido) {
		pedido.setStatus("encerrado");
		pedido.attTotal();
		PedidoRepo.save(pedido);		
	}
	
	@Transactional
	public Pedido pedidoAtual(Cliente cliente) {
		return PedidoRepo.findByStatusAndCliente("ativo", cliente);
	}
	
	public List<Pedido> pedidosEncerrados(Cliente cliente) {
		return PedidoRepo.findAllByStatusAndCliente("encerrado", cliente);
	}
	
	// Quando um pedido aberto já existe
		public void att(Long codigo, Cliente cliente) {
			Pedido pedido = PedidoRepo.findByStatusAndCliente("ativo", cliente);
			Set<Item> itens =  pedido.getItens();
			boolean exist = false;
			for (Item item : itens) {
				if(item.getPrato().getCodigo() == codigo) {
					int qtd2 = item.getQtd() + 1;
					item.setQtd(qtd2);
					itemService.cadastrarItem(item);
					exist = true;
					break;
				}
			}
			if(!exist) { // Quando o prato pedido ainda não está no pedido
				Comida prato = comidaService.buscarPrato(codigo);
				Item item = new Item(prato);
				item.setPedido(pedido);
				itemService.cadastrarItem(item);
			}
			
		}
	
}

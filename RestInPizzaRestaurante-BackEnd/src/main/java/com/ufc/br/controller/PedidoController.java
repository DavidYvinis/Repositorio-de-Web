package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Pedido;
import com.ufc.br.service.ClienteService;
import com.ufc.br.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ClienteService clienteService;
	
	private Cliente obterCliente() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;		
		Cliente cliente = clienteService.obterCliente(user.getUsername());
		return cliente;
	}

	@RequestMapping("/pedidoAtual")
	public ModelAndView pedidoAtual() {
		Cliente cliente = this.obterCliente();
		
		Pedido pedido = pedidoService.pedidoAtual(cliente);
		
		ModelAndView mv = new ModelAndView("pedidoAtual");
		mv.addObject("pedido", pedido);
		return mv;
	}
	

	@RequestMapping("/add/{codigo}")
	public ModelAndView adicionarItem(@PathVariable Long codigo) {
		Cliente cliente = this.obterCliente();
		
		Pedido atualPedido = pedidoService.pedidoAtual(cliente);
		
		if(atualPedido != null) {
			pedidoService.att(codigo, cliente);
		} else {
			pedidoService.cadastrarPedido(codigo, cliente);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/pedido/pedidoAtual");
		return mv;
	}
	
	@RequestMapping("/deletar/{codigo}")
	public ModelAndView deletarItem(@PathVariable Long codigo) {
		Cliente cliente = this.obterCliente();
		
		pedidoService.deletarItem(codigo, cliente);
		ModelAndView mv = new ModelAndView("redirect:/pedido/pedidoAtual");
		return mv;
	}
	
	@RequestMapping("/finalizarPedido")
	public ModelAndView encerrarPedido(@RequestParam(value = "endereco") String endereco) {
		Cliente cliente = this.obterCliente();
		Pedido pedido = pedidoService.pedidoAtual(cliente);
		pedido.setEndereco(endereco);
		pedidoService.finalizarPedido(pedido);
		ModelAndView mv = new ModelAndView("redirect:/pedido/pedidoAtual");
		return mv;
	}
	
	@RequestMapping("/pedidosEncerrados")
	public ModelAndView encerrarPedido() {
		Cliente cliente = this.obterCliente();
		List<Pedido> pedidosEncerrados = pedidoService.pedidosEncerrados(cliente);
		ModelAndView mv = new ModelAndView("pedidosEncerrados");
		mv.addObject("listaEncerrados", pedidosEncerrados);
		return mv;
	}
	
	
	
	
	
	
	
	
}

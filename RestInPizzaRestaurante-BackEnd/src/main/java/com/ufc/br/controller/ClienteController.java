package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.service.ClienteService;

@Controller
@RequestMapping("/client")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("CadastrarUsuario");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastro(@Validated Cliente cliente, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastrarUsuario");
		if(result.hasErrors()) {
			return mv;
		}
		clienteService.cadastrar(cliente);
		mv.addObject("mensagem", "Cadastro realizado!");
		return mv;
	}

}

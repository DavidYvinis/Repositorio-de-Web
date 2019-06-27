package com.ufc.br.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Gerente;
import com.ufc.br.service.GerenteService;

@Controller
@RequestMapping("/gerente")
public class GerenteController {
	@Autowired
	GerenteService gerenteService;
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastrar() {
		Gerente yvinis = new Gerente();
		yvinis.setCpf("474.883.938-48");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = format.parse("02/04/1999");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yvinis.setDataNascimento(date);
		yvinis.setEmail("dd@gmail.com");
		yvinis.setNome("DavidY");
		yvinis.setSenha("4444");
		gerenteService.cadastrar(yvinis);
		ModelAndView mv = new ModelAndView("cadastraGerente");
		return mv;
	}
	
	@RequestMapping("/del")
	public ModelAndView deletar() {
		gerenteService.excluir(23L);
		ModelAndView mv = new ModelAndView("cadastraGerente");
		return mv;
	}

}

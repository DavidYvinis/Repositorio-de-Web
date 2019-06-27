package com.ufc.br.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Comida;
import com.ufc.br.service.ComidaService;

@Controller
@RequestMapping("/rest")
public class ComidaController {
	
	@Autowired
	private ComidaService comidaService;
	
	
	@RequestMapping("/formulario")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject("comida", new Comida());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Validated Comida comida, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
		
		
		ModelAndView mv = new ModelAndView("Formulario");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		mv.addObject("mensagem", "Prato cadastrada com sucesso!");
		comidaService.cadastrar(comida, imagem);
		
		return mv;
}
	
	
	@GetMapping("/listar")
	public ModelAndView listarPessoas() {
		List<Comida> comidas = comidaService.listarTodos();//devolve todos os pratos
		ModelAndView mv = new ModelAndView("PaginaListagem");
		mv.addObject("listaPratos", comidas);
		
		
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public ModelAndView deletar(@PathVariable Long codigo) {
		comidaService.excluirPrato(codigo);
		ModelAndView mv = new ModelAndView("redirect:/rest/listar");
		return mv;
	}
	
	
	@RequestMapping("/atualizar/{codigo}")
	public ModelAndView atualizarPessoa(@PathVariable Long codigo) {
		Comida comida = comidaService.buscarPrato(codigo);
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject("comida", comida);
		return mv;
	}
	
	@GetMapping("/cardapio")
	public ModelAndView cardap() {
		List<Comida> comidas = comidaService.listarTudo();//devolve todas as pessoas
		ModelAndView mv = new ModelAndView("Cardapio");
		mv.addObject("listaPratos", comidas);
		
		return mv;
	}
	
//	@GetMapping("/comprar/{codigo}")
//	public ModelAndView comprar(@PathVariable Long codigo) {
//		List<Comida> comidas = (List<Comida>) comidaService.pegarUm(codigo);
//		ModelAndView mv = new ModelAndView("Comprar");
//		mv.addObject("listaPessoas", comidas);
//		return mv;
//		
//	}
	
	
	
	
	
	
	
	
	
}

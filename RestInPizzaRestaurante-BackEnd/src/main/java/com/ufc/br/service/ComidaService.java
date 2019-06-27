package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ufc.br.model.Comida;
import com.ufc.br.repository.ComidaRepository;
import com.ufc.br.util.AulaFileUtils;

@Service
public class ComidaService {
	@Autowired
	private ComidaRepository comidaRepo;
	
	
	public void cadastrar(Comida comida, MultipartFile imagem) {
		
		String caminho = "images/" + comida.getPrato() + ".png";
		
		
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		
		comidaRepo.save(comida);
		
	}


	public List<Comida> listarTodos() {
		return comidaRepo.findAll();
	}
	
	public List<Comida> listarTudo(){
		return comidaRepo.findAll();
	}


	public void excluirPrato(Long codigo) {
		comidaRepo.deleteById(codigo);
		
	}


	public Comida buscarPrato(Long codigo) {
		return comidaRepo.getOne(codigo);
	}
	
	public Comida pegarUm(Long codigo){
		
		return comidaRepo.getOne(codigo);
	}
	
}

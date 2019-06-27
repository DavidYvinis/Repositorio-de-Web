package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Item;
import com.ufc.br.repository.ItemRepository;


@Service
public class ItemService {
	@Autowired
	ItemRepository ItemRepo;
	
	public void deletarItem(Long codigo) {
		ItemRepo.deleteById(codigo);
	}
	
	public void cadastrarItem(Item item) {
		ItemRepo.save(item);
	}
	
}

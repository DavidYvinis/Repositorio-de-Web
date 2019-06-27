package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long>{
	
	
	
}

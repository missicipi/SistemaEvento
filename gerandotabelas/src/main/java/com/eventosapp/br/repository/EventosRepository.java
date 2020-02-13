package com.eventosapp.br.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.br.domain.Evento;

public interface EventosRepository extends CrudRepository<Evento, String> {

	//Para buscar um evento específico no banco
	Evento findById(Integer id);
	
}

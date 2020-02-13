package com.eventosapp.br.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.br.domain.Convidado;
import com.eventosapp.br.domain.Evento;

public interface RepositoryConvidado extends CrudRepository<Convidado, String>{

	Iterable<Convidado>findByEvento(Evento evento);//para buscar no banco os convidados através do evento
}

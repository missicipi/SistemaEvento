package com.eventosapp.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventosapp.br.domain.Convidado;
import com.eventosapp.br.domain.Evento;
import com.eventosapp.br.repository.EventosRepository;
import com.eventosapp.br.repository.RepositoryConvidado;

@Controller
public class EventoController {

	@Autowired
	private EventosRepository er;

	@Autowired
	private RepositoryConvidado cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "Evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(Evento evento) {

		er.save(evento);// salvar o evento no banco
		System.out.println("Salvo no banco");
		return "redirect:cadastrarEvento";
	}

	// para retornar uma lista
	@RequestMapping("/eventos")
	public ModelAndView ListaEventos() {

		// para renderizar
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();// fazer a busca no banco
		mv.addObject("eventos", eventos);// vai pegar o eventos do html e colocar a lista do banco
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") Integer id) {
		Evento evento = er.findById(id);
		ModelAndView mv = new ModelAndView("Evento/detalhesEvento");
		mv.addObject("evento", evento);
		System.out.println();
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesEvento1(@PathVariable("id") Integer id, Convidado convidado) {
		Evento evento = er.findById(id);// vai receber
		convidado.setEvento(evento);
		cr.save(convidado);
		return "redirect:/{id}";
	}

}

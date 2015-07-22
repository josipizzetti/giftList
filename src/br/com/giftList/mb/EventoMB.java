package br.com.giftList.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.giftList.dao.EventoDAO;
import br.com.giftList.entity.Evento;

@ManagedBean
@ViewScoped
public class EventoMB implements Serializable{

	private List<Evento> eventos;
	private Evento evento;
	
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}

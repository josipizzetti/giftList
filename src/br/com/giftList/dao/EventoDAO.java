package br.com.giftList.dao;

import br.com.giftList.entity.Evento;
import br.com.giftList.util.JpaUtil;

public class EventoDAO extends GenericDAO<Evento>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Evento buscaPorNome(String nome){
		return (Evento) JpaUtil.getEntityManager().createQuery("select e from Evento e where nome = :enome").setParameter("enome", nome).getSingleResult();
	}


}

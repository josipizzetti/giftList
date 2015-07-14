package br.com.giftList.dao;

import javax.persistence.EntityManager;

import br.com.giftList.entity.Presente;
import br.com.giftList.util.JpaUtil;

public class PresenteDAO extends GenericDAO<Presente>{
	
	EntityManager em = JpaUtil.getEntityManager();
	
	//Busca por evento?

}

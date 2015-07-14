package br.com.giftList.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.giftList.entity.Usuario;
import br.com.giftList.util.JpaUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	EntityManager em = JpaUtil.getEntityManager();
	
	public List<Usuario> buscaPorNome(String nome){
		return em.createQuery("select u from Usuario u where nome = :nome").setParameter("nome", nome).getResultList();
	}
	
	public Usuario buscaPorLogin(String email){
		return (Usuario)em.createQuery("select u from Usuario u where email = :emailUsu").setParameter("emailUsu", email).getSingleResult();
	}
	

}

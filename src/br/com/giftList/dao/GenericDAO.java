package br.com.giftList.dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.giftList.util.JpaUtil;

public class GenericDAO<T> implements Serializable{
	
	EntityManager em = JpaUtil.getEntityManager();
	
	public void salvar(T entity){
		em.getTransaction().begin(); //transaction para iniciar essa transacao e nenhum outro usuario conseguir mexer, entrara na fila
		try {
			em.persist(entity);
			em.getTransaction().commit();//qd der o commit libera a transacao para outro usuario
		} catch (Exception e) {
		em.getTransaction().rollback();
		}
	}
	
	public void inserir(T entity){
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public List<T> listar(Class classe){ 
		return em.createQuery("select x from "+classe.getSimpleName() +" x").getResultList();
	}
	
	public void atualizar(T entity){
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		
	}
	 public void deletar(T entity){
		 em.getTransaction().begin();
		 em.remove(entity);
		 em.getTransaction().commit();
		 
	 }
	 
	public T buscaId(Integer id, Class classe){
			return (T) em.find(classe, id);
		}
	

}

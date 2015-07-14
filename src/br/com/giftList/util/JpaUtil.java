package br.com.giftList.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static final String PERSISTENCE_UNIT = "giftlist";

	private static ThreadLocal<EntityManager>
	threadEntityManager = new ThreadLocal<EntityManager>();

	//Fábrica de conexões
	private static EntityManagerFactory entityManagerFactory;

	private JpaUtil() {
	}

	public static EntityManager getEntityManager() {
		//Verifica se a fábrica já foi criada
		if (entityManagerFactory == null) {
			entityManagerFactory =
					Persistence.createEntityManagerFactory(
							PERSISTENCE_UNIT);
		}


		//Obtém o entity maneger da threadEntityManager
		EntityManager entityManager = threadEntityManager.get();

		//Verifica se o entityManager existe e esta aberto
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager =
					entityManagerFactory.createEntityManager();
			JpaUtil.threadEntityManager.set(entityManager);
		}

		//retorna o entityManager criado
		return entityManager;
	}

	public static void closeEntityManager() {
		EntityManager em = threadEntityManager.get();
		if (em != null) {
			EntityTransaction transaction = em.getTransaction();
			if (transaction.isActive()) {
				transaction.commit();
			}
			em.close();
			threadEntityManager.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		closeEntityManager();
		entityManagerFactory.close();
	}

}

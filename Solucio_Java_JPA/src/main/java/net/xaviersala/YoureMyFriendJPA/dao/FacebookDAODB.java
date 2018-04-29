package net.xaviersala.YoureMyFriendJPA.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import net.xaviersala.YoureMyFriendJPA.model.Persona;

public class FacebookDAODB implements FacebookDAO {
	
	private static final String GETPERSONA = "SELECT p FROM Persona p WHERE p.id = :idpersona";

	EntityManagerFactory emf;
	EntityManager em;
	
	public FacebookDAODB() {
		emf = Persistence.createEntityManagerFactory("YoureMyFriendJPA");
		em = emf.createEntityManager();
	}
	
	@Override
	public Persona getPersona(String id) throws NoResultException {
		TypedQuery<Persona> q = em.createQuery(GETPERSONA, Persona.class);
		q.setParameter("idpersona", id);
		q.setMaxResults(1);		
		return q.getSingleResult();
	}

	@Override
	public void disconnect() {
		em.close();
		emf.close();		
	}

}

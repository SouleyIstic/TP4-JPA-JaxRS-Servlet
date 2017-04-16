package fr.tp4sir.tp4sir;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnexionManager {
	
	private static EntityManager manager;
	private static EntityManagerFactory factory;
	private static ConnexionManager m =null;


	private ConnexionManager() {
		
		factory = Persistence.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		m=this;
     	
	}
	
	
	
	public static ConnexionManager getInstance() {
		if (m == null) { // Premier appel
	         m = new ConnexionManager();
			}
		
		return m;
	}
	
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		ConnexionManager.manager = manager;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		ConnexionManager.factory = factory;
	}
}

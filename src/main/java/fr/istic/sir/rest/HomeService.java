
package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import fr.tp4sir.tp4sir.Home;
import fr.tp4sir.tp4sir.ConnexionManager;


@Path("/home")
public class HomeService {
	private ConnexionManager ConnexionManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getAllHomes() {
		this.ConnexionManager = ConnexionManager.getInstance();
		EntityManager manager = this.ConnexionManager.getManager();
		List<Home> resultList = manager.createQuery("Select h From Home as h").getResultList();
		return resultList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHomeById(@PathParam("id") long id) {
		this.ConnexionManager = ConnexionManager.getInstance();
		EntityManager manager = this.ConnexionManager.getManager();
		Home home = manager.find(Home.class, id);
		return home;
	}

	 @DELETE
	 @Path("/{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public void removeHomeById(@PathParam("id") long id ) {
	 this.ConnexionManager = ConnexionManager.getInstance();
	 EntityManager manager = this.ConnexionManager.getManager();
	 EntityTransaction tx = manager.getTransaction();
	 tx.begin();
	 manager.remove(manager.find(Home.class, id));
	 tx.commit();
	 }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addHome(Home h) {
		this.ConnexionManager = ConnexionManager.getInstance();
		EntityManager manager = this.ConnexionManager.getManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(h);
		tx.commit();
	}

}

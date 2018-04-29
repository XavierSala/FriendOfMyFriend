package net.xaviersala.YoureMyFriendJPA;

import java.util.logging.LogManager;

import javax.persistence.NoResultException;

import org.hibernate.service.spi.ServiceException;

import net.xaviersala.YoureMyFriendJPA.dao.FacebookDAO;
import net.xaviersala.YoureMyFriendJPA.dao.FacebookDAODB;
import net.xaviersala.YoureMyFriendJPA.model.Persona;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Desactivar els logs
		LogManager.getLogManager().reset();

		// Home 9a35996b-15ff-46ac-a8b1-2c091fff79f1
		// Dona 2556a150-711c-4829-bdaa-a395e18b7010
		// Inexistent 112-112
		String idABuscar = "9a35996b-15ff-46ac-a8b1-2c091fff79f1";
		try {
			FacebookDAO db = new FacebookDAODB();
			
			BuscadorDeAmics cercador = new BuscadorDeAmics(db);
			Persona candidat = cercador.getPersona(idABuscar);

			System.out.println(candidat);
			System.out.println("-----------------------------------");
			System.out.println(cercador.getAmicsDe(candidat).size() + " amics");

			db.disconnect();

		} catch (NoResultException e) {
			System.out.println("Persona inexistent " + idABuscar);
		} catch (ServiceException e) {
			System.out.println("Problemes al connectar amb la base de dades: " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

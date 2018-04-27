package net.xaviersala.YoureMyFriend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.xaviersala.YoureMyFriend.dao.FacebookDAO;
import net.xaviersala.YoureMyFriend.model.Friend;

public class BuscadorDeAmics {
	
	private static final Logger LOG = Logger.getLogger("BuscaAmics");
	

	FacebookDAO dades;
	
	/**
	 * Crea un objecte per cercar els amics dels amics.
	 * @param dades Connexió amb la base de dades
	 */
	public BuscadorDeAmics(FacebookDAO dades) {
		this.dades = dades;
	}

	/**
	 * Retorna la llista d'amics del candidat que se li passa. 
	 * @param candidat Friend del que es cerca els amics
	 * @return Llista dels amics del candidat
	 * @throws SQLException 
	 */
	public Set<Friend> getAmicsDe(Friend candidat) throws SQLException {
		
		Set<Friend> amics = new HashSet<Friend>(); 

		
		List<Friend>  nousAmics = new ArrayList<>();
		nousAmics.add(candidat);
		
		while(nousAmics.size() > 0) {
						
			// Comprova si els amics dels amics (nousAmics) són amics (i del sexe correcte)
			List<Friend> futursAmics = buscaFutursAmics(candidat, amics, nousAmics);

			if (!futursAmics.isEmpty()) {
				amics.addAll(futursAmics);
			}
			
			// Ara he de mirar els amics dels futurs amics
			nousAmics = futursAmics;	
			
			LOG.info(amics.size() + " ->  Ara tinc " + nousAmics.size() + "amics nous");
		}
							
		return amics;
		
	}

	/**
	 * Mira els amics dels amics per veure si poden ser amics
	 * @param candidat persona original
	 * @param amics Llista d'amics actuals
	 * @param nousAmics Llista dels nous amics dels que s'han de trobar més amics 
	 * @return Llista amb els nous amics trobats.
	 * @throws SQLException
	 */
	private List<Friend> buscaFutursAmics(Friend candidat, Set<Friend> amics, List<Friend> nousAmics)
			throws SQLException {
		
		List<Friend> futursAmics = new ArrayList<>();
		for(Friend f: nousAmics) {					
			for (Friend possibleAmic: dades.getMyFriends(f)
					.stream()
					.filter(it -> it.getSexe() == candidat.getSexe())
					.collect(Collectors.toList())) {
				
				if (isUnNouAmic(possibleAmic, amics)) {
					futursAmics.add(possibleAmic);
				}
			}				    									
		}
		return futursAmics;
	}


	private boolean isUnNouAmic(Friend possibleAmic, Set<Friend> amics) {
		return !amics.contains(possibleAmic);
	}
	
}

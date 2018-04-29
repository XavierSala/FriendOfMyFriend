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
		List<Friend>  darrersAmics = new ArrayList<>();
		
		darrersAmics.add(candidat);
		
		while(darrersAmics.size() > 0) {
						
			darrersAmics = buscaNousAmicsEntreElsAmicsDelsAmics(darrersAmics, amics);
			if (!darrersAmics.isEmpty()) {
				amics.addAll(darrersAmics);
			}			
			LOG.info(amics.size() + " ->  Ara tinc " + darrersAmics.size() + "amics nous");
		}
							
		return amics;
		
	}

	/**
	 * Mira els nous amics aconseguits per veure si troba nous amics.
	 * @param nousAmics Llista dels nous amics dels que s'han de trobar més amics 
	 * @param amics Llista d'amics actuals
	 * @return Llista amb els nous amics trobats.
	 * @throws SQLException
	 */
	private List<Friend> buscaNousAmicsEntreElsAmicsDelsAmics(List<Friend> nousAmics, Set<Friend> amics)
			throws SQLException {
		
		List<Friend> futursAmics = new ArrayList<>();
		for(Friend f: nousAmics) {					
			futursAmics.addAll(dades.getMyFriends(f)
					.stream()
					.filter(it -> !amics.contains(it))
					.collect(Collectors.toList()));				
		}
		return futursAmics;
	}
	
}

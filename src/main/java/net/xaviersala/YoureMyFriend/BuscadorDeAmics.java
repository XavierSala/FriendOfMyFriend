package net.xaviersala.YoureMyFriend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.xaviersala.YoureMyFriend.model.Friend;

public class BuscadorDeAmics {
	
	private static final Logger LOG = Logger.getLogger("BuscaAmics");
	

	FacebookDAO dades;
	
	public BuscadorDeAmics(FacebookDAO dades) {
		this.dades = dades;
	}

	public Set<Friend> getAmicsDe(Friend candidat) throws SQLException {
		
		Set<Friend> amics = new HashSet<Friend>(); 

		
		List<Friend>  nousAmics = new ArrayList<>();
		nousAmics.add(candidat);
		
		while(nousAmics.size() > 0) {
			
			List<Friend> futursAmics = new ArrayList<>();		
			
			// Comprova si els amics dels amics són amics
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

			amics.addAll(futursAmics);

			nousAmics = futursAmics;	
			LOG.info(amics.size() + " ->  Ara tinc " + nousAmics.size() + "amics nous");
		}
							
		return amics;
		
	}


	private boolean isUnNouAmic(Friend possibleAmic, Set<Friend> amics) {
		return !amics.contains(possibleAmic);
	}
	
}

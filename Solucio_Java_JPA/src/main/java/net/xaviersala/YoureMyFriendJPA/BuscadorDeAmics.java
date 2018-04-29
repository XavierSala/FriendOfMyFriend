package net.xaviersala.YoureMyFriendJPA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import net.xaviersala.YoureMyFriendJPA.dao.FacebookDAO;
import net.xaviersala.YoureMyFriendJPA.model.Amic;
import net.xaviersala.YoureMyFriendJPA.model.Persona;

public class BuscadorDeAmics {
	
	private static final Logger LOG = Logger.getLogger("BuscaAmics");
	
	FacebookDAO dades;
	FileHandler fh;  
	
	/**
	 * Crea un objecte per cercar els amics dels amics.
	 * @param dades Connexió amb la base de dades
	 */
	public BuscadorDeAmics(FacebookDAO dades) {
		this.dades = dades;
	}
	
	public Persona getPersona(String id) throws SecurityException, IOException {
		fh = new FileHandler("amics.log");  
		LOG.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
		return dades.getPersona(id);
	}
	
	/**
	 * Retorna la llista d'amics del candidat que se li passa. 
	 * @param candidat Friend del que es cerca els amics
	 * @return Llista dels amics del candidat
	 * @throws SQLException 
	 */
	public List<String> getAmicsDe(Persona candidat) throws SQLException {
		
		Map<String, String> amics = new HashMap<>(); 
		
		List<Persona>  darrersAmics = new ArrayList<>();
		
		darrersAmics.add(candidat);
		while(darrersAmics.size() > 0) {
			darrersAmics = buscaNousAmicsEntreElsAmicsDelsAmics(darrersAmics, amics);
			darrersAmics.stream().forEach( it -> {
					LOG.fine("Afegit amic " + it.getId() + " " + it.getNom());
					amics.put(it.getId(), it.getNom());
				}
			);
			LOG.info(amics.size() + " ->  he afegit " + darrersAmics.size() + "amics nous");
		}
							
		return new ArrayList<String>(amics.values());
		
	}

	/**
	 * Busca els amics dels amics de la llista i en torna només els que són nous
	 * @param darrersAmics Llista dels nous amics dels que trobar amics
	 * @param amics Amics que ja tenim (es fa servir per filtrar)
	 * @return Llista amb els nous amics
	 */
	private List<Persona> buscaNousAmicsEntreElsAmicsDelsAmics(
			List<Persona> darrersAmics, 
			final Map<String, String> amics) {
		
	
		List<Persona> nous = new ArrayList<Persona>();
		for(Persona persona : darrersAmics) {
			for(Amic amic : persona.getAmics()) {
				Persona tmp = amic.getAmic();
				if (tmp.getSexe().getId() == persona.getSexe().getId() &&
					!amics.containsKey(tmp.getId())) {					
						nous.add(tmp);					
				}
			}
		}
		return nous;
	}

//	/**
//	 * Bàsicament és el mateix que el mètode buscaNousAmicsEntreElsAmicsDelsAmics però fent servir 
//   * els streams de Java8 (no es fa servir)
//	 * @param darrersAmics Llista amb els amics dels que recuperar els amics
//	 * @param amics Lista dels amic existents (per filtrar)
//	 * @return Llista amb els amics nous
//	 */
//	private List<Persona> buscaNousAmicsEntreElsAmicsDelsAmics2(List<Persona> darrersAmics, final Map<String, String> amics) {
//		List<Persona> nous = new ArrayList<Persona>();
//		for(Persona persona : darrersAmics) {
//			nous.addAll(
//					persona.getAmics().stream()
//					                  .map(it -> it.getAmic())
//					                  .filter(a -> a.getSexe().getId() == persona.getSexe().getId())
//					                  .filter(a -> !amics.containsKey(a.getId()))
//					                  .collect(Collectors.toList())
//					);			
//		}
//		return nous;
//	}
//
	
}

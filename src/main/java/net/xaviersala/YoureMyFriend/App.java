package net.xaviersala.YoureMyFriend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.xaviersala.YoureMyFriend.model.Friend;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger LOG = Logger.getLogger("App");
	
	

    public static void main( String[] args ) throws FileNotFoundException
    {
    	// Desactivar els logs
    	LogManager.getLogManager().reset();

    	
        try {
			// FacebookDAO dades = new FacebookDAOMySQL("jdbc:mysql://localhost/facebook", "root", "ies2010");7
			FacebookDAO dades = new FacebookDAOPostgresSQL("jdbc:postgresql://localhost/facebook", "postgres", "ies2010");
			// N'agafo un quasevol (per no entrar-lo a mà)
			final Friend candidat = dades.getRandomNames(5).get(0);
			
			// Òscar Llach Ingla: no té cap amic (només amigues)
			// final Friend candidat = dades.getFriend("d6cf5345-1e97-495b-8d33-47e1dd864e54");
			
			
			System.out.println(candidat);
			System.out.println("-------------------");
						
			BuscadorDeAmics busca = new BuscadorDeAmics(dades);			
			Set<Friend> amics = busca.getAmicsDe(candidat);
			
			System.out.println("--> Té : " + amics.size() + " amics");
			
			// Guardo el resultat en un fitxer
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(out);
			amics.forEach(System.out::println);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }

//	private static boolean esAmic(String id) {
//		return amics.stream()
//				    .filter(o -> o.getId().equals(id))
//				    .findFirst()
//				    .isPresent();
//	}


}

package net.xaviersala.YoureMyFriend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.xaviersala.YoureMyFriend.dao.FacebookDAO;
import net.xaviersala.YoureMyFriend.dao.FacebookDAOMySQL;
import net.xaviersala.YoureMyFriend.model.Friend;

/**
 * Programa que determina qui són els amics d'un determinat
 * "friend"
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	// Desactivar els logs
    	LogManager.getLogManager().reset();

    	
        try {
			FacebookDAO dades = new FacebookDAOMySQL("jdbc:mysql://localhost/facebook", "root", "ies2010");
			// FacebookDAO dades = new FacebookDAOPostgresSQL("jdbc:postgresql://localhost/facebook", "postgres", "ies2010");
			
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
}

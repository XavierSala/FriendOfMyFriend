package net.xaviersala.YoureMyFriend.dao;

import java.sql.SQLException;
import java.util.List;
import net.xaviersala.YoureMyFriend.model.Friend;

public interface FacebookDAO {
	
	/**
	 * Retorna una llista aleatòria amb un número determinat de Friends
	 * @param numero Número de resultats a obtenir
	 * @return Llista de Friends
	 * @throws SQLException 
	 */
	List<Friend> getRandomNames(int numero) throws SQLException;
	/**
	 * Retorna una llista amb els amics directes del friend especificat.
	 * @param id Friend del que hem d'extreure els amics
	 * @return Llista amb els amics de id
	 * @throws SQLException
	 */
	List<Friend> getMyFriends(Friend id) throws SQLException;
	/**
	 * Retorna l'amic que té l'id especificat o bé null.
	 * @param id id que es busca
	 * @return Friend que té l'id especificat
	 * @throws SQLException
	 */
	Friend getFriend(String id) throws SQLException;
	
}

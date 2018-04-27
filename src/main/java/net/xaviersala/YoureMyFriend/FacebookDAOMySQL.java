package net.xaviersala.YoureMyFriend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.xaviersala.YoureMyFriend.model.Friend;

public class FacebookDAOMySQL implements FacebookDAO {
	
	final static String GETFRIEND = "SELECT p.id, p.nom, p.sexe FROM PERSONES p WHERE p.id = ?";
	final static String RANDOMFRIENDS = "SELECT p.id, p.nom, p.sexe FROM PERSONES p "			                         
			                          + "ORDER BY RAND() LIMIT ?";
	final static String MYFRIENDS = "SELECT p.id, p.nom, p.sexe FROM AMICS a "
			                      + "INNER JOIN PERSONES p ON p.id=a.id2 "
			                      + "WHERE a.id1 = ? and p.sexe = ?";

	Connection conn;
	
	public FacebookDAOMySQL(String jdbc, String user, String pass) throws SQLException {
		conn = DriverManager.getConnection(jdbc, user, pass);
	}

	public List<Friend> getRandomNames(int numero) throws SQLException {
		List<Friend> friends = new ArrayList<>();
		PreparedStatement st = conn.prepareStatement(RANDOMFRIENDS);
		st.setInt(1, numero);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			Friend friend = new Friend();
			friend.setId(rs.getString(1));
			friend.setNom(rs.getString(2));
			friend.setSexe(rs.getInt(3));
			friends.add(friend);
		}
		
		return friends;
	}

	public List<Friend> getMyFriends(Friend friend) throws SQLException {
		List<Friend> friends = new ArrayList<>();
		PreparedStatement st = conn.prepareStatement(MYFRIENDS);
		st.setString(1, friend.getId());
		st.setInt(2, friend.getSexe());
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			Friend noufriend = new Friend();
			noufriend.setId(rs.getString(1));
			noufriend.setNom(rs.getString(2));
			noufriend.setSexe(rs.getInt(3));
			friends.add(noufriend);
		}
		
		return friends;		
	}

	@Override
	public Friend getFriend(String id) throws SQLException {
		PreparedStatement st = conn.prepareStatement(GETFRIEND);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		Friend friend = null;
		while (rs.next()) {
			friend = new Friend();
			friend.setId(rs.getString(1));
			friend.setNom(rs.getString(2));
			friend.setSexe(rs.getInt(3));
		}
		return friend;
	}

}

package net.xaviersala.YoureMyFriend;

import java.sql.SQLException;
import java.util.List;
import net.xaviersala.YoureMyFriend.model.Friend;

public interface FacebookDAO {
	
	List<Friend> getRandomNames(int numero) throws SQLException;
	List<Friend> getMyFriends(Friend id) throws SQLException;
	Friend getFriend(String id) throws SQLException;
	
}

package net.xaviersala.YoureMyFriendJPA.dao;

import net.xaviersala.YoureMyFriendJPA.model.Persona;

public interface FacebookDAO {
	Persona getPersona(String id);
	void disconnect();
	
}

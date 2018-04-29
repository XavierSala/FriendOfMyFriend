package net.xaviersala.YoureMyFriend.model;

public class Friend {
	String id;
	String nom;
	int sexe;
	
	public Friend() {
		
	}
	
	public Friend(String string, String string2, int i) {
		this.id = string;
		this.nom = string2;
		this.sexe = i;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getSexe() {
		return sexe;
	}
	
	public void setSexe(int sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return id + " " + nom + " " + sexe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Friend))
			return false;
		Friend other = (Friend) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
//	}
//	
//	@Override
//	public int compareTo(Friend o) {		
//		return id.compareTo(o.getId());
//	}
//	
	
}

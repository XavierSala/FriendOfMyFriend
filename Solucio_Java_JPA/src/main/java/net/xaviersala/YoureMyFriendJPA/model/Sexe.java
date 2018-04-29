package net.xaviersala.YoureMyFriendJPA.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sexe
 *
 */
@Entity
@Table(name="SEXES")
public class Sexe implements Serializable {

	   
	@Id
	private int id;
	private String tipus;
	
	private static final long serialVersionUID = 1L;

	public Sexe() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTipus() {
		return this.tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	@Override
	public String toString() {
		return tipus;
	}
   
	
}

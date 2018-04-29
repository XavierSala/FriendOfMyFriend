package net.xaviersala.YoureMyFriendJPA.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Table(name="PERSONES")
public class Persona implements Serializable {

	   
	@Id
	private String id;
	private String nom;
	@OneToOne
	@JoinColumn(name="sexe")
	private Sexe sexe;
	
	@OneToMany
	@JoinColumn(name="id1")
	private List<Amic> amics;

	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public List<Amic> getAmics() {
		return this.amics;
	}

	public void setAmics(List<Amic> amics) {
		this.amics = amics;
	}   
	public Sexe getSexe() {
		return this.sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return id + " " + nom + " (" + sexe.getTipus() + ")";
	}

	
	
   
}

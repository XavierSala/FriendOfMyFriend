package net.xaviersala.YoureMyFriendJPA.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Amic
 *
 */
@Entity
@Table(name="AMICS")
public class Amic implements Serializable {

	   
	@Id
	private int codi;
	@ManyToOne
	@JoinColumn(name="id1")
	private Persona persona;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id2")
	private Persona amic;
	
	private static final long serialVersionUID = 1L;

	public Amic() {
		super();
	}   
	public int getCodi() {
		return this.codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}   
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}   
	public Persona getAmic() {
		return this.amic;
	}

	public void setAmic(Persona amic) {
		this.amic = amic;
	}
	@Override
	public String toString() {
		return "" + amic;
	}
	
	
   
}

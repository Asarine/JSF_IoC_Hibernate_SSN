package fr.adaming.toulouse.SSN.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrateurs")
public class Administrateur implements Serializable {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ad")
	private int id;
	private String mail;
	private String mdp;
	
	// Constructeurs
	public Administrateur() {
		super();
	}
	public Administrateur(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}
	public Administrateur(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	// Getters et setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
	// Red�finition toString
	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	

}

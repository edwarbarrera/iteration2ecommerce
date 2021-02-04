package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="employe")
public class Employe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_employe")
	private int id;
	
	@Column(name="nom")
	private String nom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + "]";
	}
	public Employe(String nom) {
		super();
		this.nom = nom;
	}
	
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}


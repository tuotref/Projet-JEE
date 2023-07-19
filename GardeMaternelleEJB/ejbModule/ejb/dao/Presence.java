package ejb.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Presence implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id  ; 
	
	@Column(name = "HeureA")
	LocalDateTime startHour ; 
	
	@Column(name = "HeureD")
	LocalDateTime departHour ; 

	@Column(name = "JourDePresence")
	private LocalDate day ;
	
	@Column(name="indemnitesEntretien")
	private boolean entretien ; 
	
	@Column(name="indemnitesRepat")
	private boolean repat ; 
	
	@Column(name = "sommeDujour")
	private double total ; 

	
	 @ManyToOne
	 private Enfant enfant;


	public LocalDateTime getStartHour() {
		return startHour;
	}


	public void setStartHour(LocalDateTime startHour) {
		this.startHour = startHour;
	}


	public LocalDateTime getDepartHour() {
		return departHour;
	}


	public void setDepartHour(LocalDateTime departHour) {
		this.departHour = departHour;
	}


	public LocalDate getDay() {
		return day;
	}


	public void setDay(LocalDate day) {
		this.day = day;
	}


	public boolean isEntretien() {
		return entretien;
	}


	public void setEntretien(boolean entretien) {
		this.entretien = entretien;
	}


	public boolean isRepat() {
		return repat;
	}


	public void setRepat(boolean repat) {
		this.repat = repat;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Enfant getEnfant() {
		return enfant;
	}


	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	} 
	 
	 
	 

	

}

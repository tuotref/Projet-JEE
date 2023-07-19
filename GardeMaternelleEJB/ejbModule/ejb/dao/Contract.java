package ejb.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
@Entity
public class Contract implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id  ; 
	
	@Column(name = "reference")
	private String reference ; 

	@Column(name = "contratDebut")
	private LocalDate contractDebute ;
	
	@Column(name = "contratFin")
	private LocalDate contractEnd ;
	
	@Column(name = "tarif")
	private double tarif ;
	
	@Column(name = "somme")
	private double total ; 
	
	@Column(name = "adresse")
	private String adressParent ; 
	
	@Column(name = "etat", columnDefinition = "boolean default false")
	private boolean etat ; 
	
	 @ManyToOne
	 private Enfant enfant; 
	
	@Transient
	private double contractHours  ; 
	
	public Contract() {
		
		this.total = this.tarif * this.contractHours   ; 

	}
	
	
	
	
	public Contract(String reference, LocalDate contractDebute, LocalDate contractEnd, double tarif,
			String adressParent,Enfant enfant) {
		super();
		this.reference = reference;
		this.contractDebute = contractDebute;
		this.contractEnd = contractEnd;
		this.tarif = tarif;
		this.adressParent = adressParent;
		this.enfant = enfant ; 
		
		//this represente the total hours depending on contract start and end
	    this.contractHours = ChronoUnit.HOURS.between(contractDebute.atStartOfDay(), contractEnd.atStartOfDay());

		
	}

	public String getAdressParent() {
		return adressParent;
	}
	public void setAdressParent(String adressParent) {
		this.adressParent = adressParent;
	}
	public LocalDate getContractDebute() {
		return contractDebute;
	}
	public void setContractDebute(LocalDate contractDebute) {
		this.contractDebute = contractDebute;
	}
	public LocalDate getContractEnd() {
		return contractEnd;
	}
	public void setContractEnd(LocalDate contractEnd) {
		this.contractEnd = contractEnd;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}




	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}




	public Enfant getEnfant() {
		return enfant;
	}




	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getEtat() {
		if (etat) return "En cours";
		return "Terminer" ; 
	}




	public void setEtat(boolean etat) {
		this.etat = etat;
	}




	public double getTotal() {
		return total;
	}




	public void setTotal(double total) {
		this.total = total;
	}




	public double getContractHours() {
		return contractHours;
	}




	public void setContractHours(double contractHours) {
		this.contractHours = contractHours;
	} 
	
	
	
	
	
}

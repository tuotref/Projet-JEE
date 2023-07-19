package ejb.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
public class Enfant implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id  ; 
	
	@Column(name = "prenom") 
	private String fName ; 
	
	@Column(name = "nom")
	private String lName ; 
	
	@Column(name = "dateNaissance")
	private Date dateOfBirth  ;
	
	@Column(name = "maman")
	private String motherName ; 
		
	
	@Column(name = "papa")
	private String fatherName ; 
	
	@Column(name = "telephoneParent")
	private String telephone ; 
	
	@ManyToOne
	private Assistante assistante  ;  

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "enfant",fetch = FetchType.LAZY)
	private List<Contract> contracts ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "enfant",fetch = FetchType.LAZY)
	private List<Presence> presences ;
	
	
	public Enfant() {
		
	}
	
	public Enfant(String fName, String lName, Date dateOfBirth, String motherName, 
		String fatherName, Assistante assistante)  {

			
			this.fName = fName ; 
			this.lName = lName ; 
			this.dateOfBirth = dateOfBirth ; 
			this.motherName = motherName ; 
			this.fatherName = fatherName ; 
			this.assistante = assistante ; 
		
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Assistante getAssistante() {
		return assistante;
	}

	public void setAssistante(Assistante assistante) {
		this.assistante = assistante;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Presence> getPresences() {
		return presences;
	}

	public void setPresences(List<Presence> presences) {
		this.presences = presences;
	}


	
	
	
	
	
	
	
	

}

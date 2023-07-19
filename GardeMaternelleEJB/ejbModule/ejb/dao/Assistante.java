package ejb.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;



@Entity
public class Assistante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id  ; 
	
	@Column(name = "prenom")
	private String fName ; 
	
	@Column(name = "nom")
	private String lName ; 
	
	@Column(name="NomUtilisateur")
	private String username ; 
	
	@Column(name="MotDepasse")
	private String password ; 
	
	@Column(name = "dateNaissance")
	private Date dateOfBirth  ;
	
	@Column(name="agrement")
	private int maxEnfant ;
	
	
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "assistante",fetch = FetchType.EAGER)
	private List<Enfant> enfants ;

	public Assistante() {
		// TODO Auto-generated constructor stub
	}

	
	public Assistante(long id, String fName, String lName, String username, String password, Date dateOfBirth,
			int maxEnfant) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.maxEnfant = maxEnfant;
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

	public int getMaxEnfant() {
		return maxEnfant;
	}

	public void setMaxEnfant(int maxEnfant) {
		this.maxEnfant = maxEnfant;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 

	public List<Enfant> getEnfants() {
		return enfants;
	}


	public void setEnfants(List<Enfant> enfants) {
		this.enfants = enfants;
	} 
	
	
	
	
	
	
	
	
	


}

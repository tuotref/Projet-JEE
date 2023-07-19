package ejb.dao.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import ejb.dao.Admin;
import ejb.dao.Assistante;
import ejb.dao.Contract;
import ejb.dao.Enfant;
import ejb.dao.Presence;

@Local
public interface IGardeMaternellLocal {
	
	//Admin
	public Admin getAdminPerUsername(String username) ; 

	//Enfant
	public Enfant addEnfant(Enfant en) ;
	public Enfant getEnfant(long id) ;
	public void UpdateEnfant(Enfant e) ;
	public void deleteEnfant(Enfant e) ; 
	public List<Enfant> getAllEnfant() ; 
	public List<Enfant> getAllEnfantPerAssistante(Assistante a) ; 
	
	//Assistante
	public Assistante getAssistante(long id) ;
	public Assistante addAssistante(Assistante a) ;
	public void updateAssistante(Assistante a) ; 
	public void deleteAssistante(Assistante a) ; 
	public List<Assistante> listAssistante() ;
	public Assistante getAssistantePerUsername(String username) ; 
	
	
	//Contrats
	public Contract addContract(Contract c) ; 
	public Contract getContract(long id) ; 
	public void updateContract(Contract contract) ; 
	public void deleteContract(Contract contract) ; 
	public List<Contract> getAllContracts() ; 
	
	
	//Presence
	public Presence addPresence(Presence p) ;
	public Presence getPresence(long id) ;
	public void updatePresence(Presence p) ;
	public void deletePresence(Presence p) ;
	public List<Presence> getAllPresence() ; 
	public List<Presence> getAllEnfantPerEnfant(Enfant e) ; 	
	
	
	//Statistiques
	public int getNombreDesEnfants() ; 
	public int getNombreDesAssistantes() ; 
	public int getNombreDesContratActifs() ; 


	
	
}

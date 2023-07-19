package ejb.dao.jpa;

import java.util.List;

import javax.ejb.Remote;

import ejb.dao.Assistante;
import ejb.dao.Enfant;

@Remote
public interface GardeMaternelleRemote {
	public Enfant addEnfant(Enfant en) ;
	public Enfant getEnfant(long id) ;
	public List<Enfant> getAllEnfant() ; 
	public Assistante getAssistante(long id) ;
	public Assistante addAssistante(Assistante a) ;
	public List<Assistante> listAssistante() ;
	public Assistante getAssistantePerUsername(String username) ;
}

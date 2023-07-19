package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import ejb.dao.Assistante;
import ejb.dao.Enfant;
import ejb.dao.jpa.IGardeMaternellLocal;

@Path("/")
public class GardeMaternelleRestService {
	
	@EJB
	private IGardeMaternellLocal metier  ;

	public Enfant addEnfant(Enfant en) {
		return metier.addEnfant(en);
	}

	public Enfant getEnfant(long id) {
		return metier.getEnfant(id);
	}

	public List<Enfant> getAllEnfant() {
		return metier.getAllEnfant();
	} 
	
	public Assistante getAssistante(long id) {
		return metier.getAssistante(id) ; 
	}
	
	public Assistante addAssistante(Assistante a) {
		return metier.addAssistante(a) ; 
	}
	
	

}

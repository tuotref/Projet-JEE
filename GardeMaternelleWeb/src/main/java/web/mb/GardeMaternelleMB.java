package web.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import ejb.dao.Assistante;
import ejb.dao.Contract;
import ejb.dao.Enfant;
import ejb.dao.jpa.IGardeMaternellLocal;

@Named("gardeMB")
@SessionScoped
public class GardeMaternelleMB implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	@EJB
	IGardeMaternellLocal metier ;
	private Assistante assistante = new Assistante() ; 
	private Contract newContract = new Contract() ; 
	private Assistante selectedAssistante = new Assistante() ; 
	private Enfant selectedEnfant = new Enfant() ; 
	private List<Assistante> listAssistantes ; 
	private List<Enfant> listEnfants ; 
	private List<Contract> listContracts ; 
	
	
	public GardeMaternelleMB() { 
		
	}
	
	
	
	public String login() {
	        Assistante assistance = metier.getAssistantePerUsername(this.assistante.getUsername());
	        if (assistance != null && assistance.getPassword().equals(this.assistante.getPassword())) {
	                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	                session.setAttribute("Assistante", assistance);
	                Assistante a = (Assistante) session.getAttribute("Assistante");
	                System.out.println(a.getfName());
	                return "Acceuil.xhtml?faces-redirect=true";
	            
	        }else {
	        	FacesContext.getCurrentInstance().addMessage(null,
		        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password.", null));
	        	return "login.xhtml?faces-redirect=true&errorMsg=Mot de passe ou Nom d'utilisateur incorrect";
	        }
	    

	}
	
	public void logout() {
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(false);
		    if (session != null) {
		        session.invalidate(); // Destroy the session

		    }
		    try {
		        externalContext.redirect("http://localhost:8080/GardeMaternelleWeb/Assistante/login.xhtml"); // Redirect to the login page
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	public void checkLoggedInAssistante() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(false);
	    
	    if ((session == null || session.getAttribute("Assistante") == null) && !externalContext.getRequestServletPath().contains("/Assistante/login.xhtml")) {
	        try {
	        	session.invalidate() ; 
	            externalContext.redirect("/GardeMaternelleWeb/Assistante/login.xhtml");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void checkSessionValidity() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(false);

	    if (session != null && !session.isNew()) {
	        session.invalidate();
	    }
	}
	
	
	public void initDataTables() {
		listAssistantes = metier.listAssistante() ; 
		listEnfants = metier.getAllEnfant() ; 
		listContracts = metier.getAllContracts() ; 
		
		
	}
	public void initAssistanteDataTable() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Assistante loggedInAssistante = (Assistante) externalContext.getSessionMap().get("Assistante");
		listEnfants = metier.getAllEnfantPerAssistante(loggedInAssistante) ;
		
	}
	
	//initialization of the form 
	public void initAssistanteForm() {
		long code;		
		code = Long.parseLong(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap()
						.get("id"));

		Assistante assis =new Assistante();
		assis = metier.getAssistante(code);
		
		if (assis!=null)
			this.selectedAssistante = assis;
			
	}
	
	public void initEnfantForm() {
		long code;		
		code = Long.parseLong(FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap()
						.get("id"));

		Enfant en =new Enfant();
		en= metier.getEnfant(code);
		
		if (en!=null)
			this.selectedEnfant = en;
			
	}

 
	public Assistante getAssistante() {
		return assistante;
	}


	public void setAssistante(Assistante assistante) {
		this.assistante = assistante;
	}


	public Assistante getSelectedAssistante() {
		return selectedAssistante;
	}


	public void setSelectedAssistante(Assistante selectedAssistante) {
		this.selectedAssistante = selectedAssistante;
	}


	public List<Assistante> getListAssistantes() {
		return listAssistantes;
	}


	public void setListAssistantes(List<Assistante> listAssistantes) {
		this.listAssistantes = listAssistantes;
	}


	public List<Enfant> getListEnfants() {
		return listEnfants;
	}


	public void setListEnfants(List<Enfant> listEnfants) {
		this.listEnfants = listEnfants;
	}


	public Enfant getSelectedEnfant() {
		return selectedEnfant;
	}


	public void setSelectedEnfant(Enfant selectedEnfant) {
		this.selectedEnfant = selectedEnfant;
	}


	public List<Contract> getListContracts() {
		return listContracts;
	}


	public void setListContracts(List<Contract> listContracts) {
		this.listContracts = listContracts;
	}


	public Contract getNewContract() {
		return newContract;
	}


	public void setNewContract(Contract newContract) {
		this.newContract = newContract;
	}
	
	
	
	
	
	
	
}

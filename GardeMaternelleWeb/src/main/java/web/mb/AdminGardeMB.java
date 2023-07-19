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
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional; 

import ejb.dao.Admin;
import ejb.dao.Assistante;
import ejb.dao.Contract;
import ejb.dao.Enfant;
import ejb.dao.jpa.IGardeMaternellLocal;

@Named("adminMB")
@SessionScoped
public class AdminGardeMB implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@EJB
	IGardeMaternellLocal metier ;
	private Assistante assistante = new Assistante() ; 
	private Admin admin = new Admin() ; 
	private Contract contract = new Contract() ; 
	private Enfant enfant = new Enfant()  ;
	private Assistante selectedAssistante = new Assistante() ; 
	private Contract selectedContract = new Contract() ; 
	private Enfant selectedEnfant = new Enfant() ; 
	private List<Assistante> listAssistantes ; 
	private List<Enfant> listEnfants ; 
	private List<Contract> listContracts ; 

	
	
	public AdminGardeMB() {
		
	}
	
	
	public String login() {
        Admin admin = metier.getAdminPerUsername(this.admin.getUsername());
        if (admin != null && admin.getPassword().equals(this.admin.getPassword())) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("Admin", admin);
                Admin a = (Admin) session.getAttribute("Admin");
                System.out.println(a.getfName());
                return "dashboard.xhtml?faces-redirect=true";
            
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
		        externalContext.redirect("http://localhost:8080/GardeMaternelleWeb/Admin/login.xhtml"); // Redirect to the login page
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	
	public void checkLoggedIn() {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    HttpSession session = (HttpSession) externalContext.getSession(false);
	    
	    if ((session == null || session.getAttribute("Admin") == null) && !externalContext.getRequestServletPath().contains("/Admin/login.xhtml")) {
	        try {
	        	session.invalidate() ; 
	            externalContext.redirect("/GardeMaternelleWeb/Admin/login.xhtml");
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
	public void initAssistanceForm() {
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

		Enfant assis =new Enfant();
		assis = metier.getEnfant(code);
		
		if (assis!=null)
			this.selectedEnfant = assis;
			
	}
	
//	public void initContractForm() {
//		long code;		
//		code = Long.parseLong(FacesContext.getCurrentInstance()
//						.getExternalContext().getRequestParameterMap()
//						.get("id"));
//
//		Assistante assis =new Assistante();
//		assis = metier.get(code);
//		
//		if (assis!=null)
//			this.selectedAssistante = assis;
//			
//	}
	
	//Enfant CRUD 
	
	public String addEnfant() { 
		metier.addEnfant(this.selectedEnfant) ; 
		return "addEnfant?msg=enfant addeed&faces-redirect=true" ; 
	}
	
	public String addAssistante() { 
		metier.addAssistante(this.selectedAssistante) ; 
		return "addAssistante?msg=assistance addeed&faces-redirect=true" ; 
	}
	
	public String editEnfant() { 
		metier.UpdateEnfant(this.selectedEnfant) ; 
		return "listeEnfants?faces-redirect=true" ; 
	}
	
	public int getNombreDesEnfants() { 
		
		int nbEnfant = metier.getNombreDesEnfants() ; 
		return nbEnfant ; 
	}
		
	public int getNombreDesAssistantes() { 
			
			int nbAssistante = metier.getNombreDesAssistantes() ; 
			return nbAssistante; 
		}
	
	public int getNombreDesContratsActif() { 
		
		int nbContract = metier.getNombreDesContratActifs() ; 
		return nbContract ; 
	}


	public Assistante getAssistante() {
		return assistante;
	}


	public void setAssistante(Assistante assistante) {
		this.assistante = assistante;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public Contract getContract() {
		return contract;
	}


	public void setContract(Contract contract) {
		this.contract = contract;
	}


	public Enfant getEnfant() {
		return enfant;
	}


	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}


	public Assistante getSelectedAssistante() {
		return selectedAssistante;
	}


	public void setSelectedAssistante(Assistante selectedAssistante) {
		this.selectedAssistante = selectedAssistante;
	}


	public Contract getSelectedContract() {
		return selectedContract;
	}


	public void setSelectedContract(Contract selectedContract) {
		this.selectedContract = selectedContract;
	}


	public Enfant getSelectedEnfant() {
		return selectedEnfant;
	}


	public void setSelectedEnfant(Enfant selectedEnfant) {
		this.selectedEnfant = selectedEnfant;
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


	public List<Contract> getListContracts() {
		return listContracts;
	}


	public void setListContracts(List<Contract> listContracts) {
		this.listContracts = listContracts;
	}
	
	
	@Transactional
	public List<Enfant> getChildrenForAssistant(Assistante assistant) {
        return (List<Enfant>) metier.getAllEnfantPerAssistante(assistant);
    }




	

}

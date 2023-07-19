package ejb.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejb.dao.Admin;
import ejb.dao.Assistante;
import ejb.dao.Contract;
import ejb.dao.Enfant;
import ejb.dao.Presence;


@Stateless(name="GM") 
public class GardeMaternelleImpl implements IGardeMaternellLocal,GardeMaternelleRemote {

	@PersistenceContext
	private EntityManager em ;  
	
	//Enfant
	
	@Override
	public Enfant addEnfant(Enfant en) {
		if(en.getAssistante().getMaxEnfant() <= en.getAssistante().getEnfants().size()) {
			em.persist(en); 
			em.flush();
			return en ; 
		}else {
			System.out.println("Already has max of childs ");
			return null ; 
		}
	
		
	}

	@Override
	public Enfant getEnfant(long id) {
		Enfant e = em.find(Enfant.class, id) ; 
		if(e==null) throw new RuntimeException()  ; 
		return e; 
	}
	
	@Override
	public void UpdateEnfant(Enfant e) {
		em.merge(e);
	}
	
	@Override
	public void deleteEnfant(Enfant e) {
		e = em.merge(e) ; 
		em.remove(e);
	}

	@Override
	public List<Enfant> getAllEnfant() {
			Query req = em.createQuery("select e from Enfant e") ; 
			return req.getResultList() ; 
	
	}
	
	@Override
	public List<Enfant> getAllEnfantPerAssistante(Assistante a) {
		Query req = em.createQuery("select e from Enfant e where e.assistante = :assistante",Enfant.class).setParameter("assistante", a) ; 
		return req.getResultList() ; 
	
	}
	
	//Assistante

	@Override
	public Assistante getAssistante(long id) {
		Assistante a = em.find(Assistante.class, id) ; 
		if(a==null) throw new RuntimeException()  ; 
		
		return a; 
	}

	@Override
	public Assistante addAssistante(Assistante a) {
		em.persist(a);
		em.flush();
		return a ; 
	}
	

	@Override
	public void updateAssistante(Assistante a ) {
		em.merge(a);
	}

	@Override
	public List<Assistante> listAssistante() {
		Query req = em.createQuery("select a from Assistante a") ; 
		return req.getResultList() ; 
	}

	@Override
	public Assistante getAssistantePerUsername(String username) {
		Query req = em.createQuery("select a from Assistante a where a.username = :username",Assistante.class).setParameter("username", username)  ; 
		try {
			return (Assistante) req.getSingleResult() ; 
			
		}catch(NoResultException e) {
			return null ; 
			
		}
		
		
	
	}


	//Contracts 
	
	@Override
	public Contract addContract(Contract c) {
		em.persist(c);
		return c ; 
	}
	
	@Override
	public void updateContract(Contract c) {
		em.merge(c) ; 
	}
	
	@Override
	public void deleteContract(Contract c) {
		c = em.merge(c) ; 
		em.remove(c);
	}
	
	@Override
	public List<Contract> getAllContracts() {
		Query req = em.createQuery("select c from Contract c") ; 
		return req.getResultList() ;  
	}

	@Override
	public Admin getAdminPerUsername(String username) {
		Query req = em.createQuery("select a from Admin a where a.username = :username",Admin.class).setParameter("username", username)  ; 
		try {
			return (Admin) req.getSingleResult() ; 
			
		}catch(NoResultException e) {
			return null ; 
			
		}
	}
	

	@Override
	public void deleteAssistante(Assistante a) {
		a = em.merge(a) ; 
		em.remove(a);
	}

	@Override
	public Contract getContract(long id) {
		Contract c = em.find(Contract.class, id)  ; 
		if(c==null) new RuntimeException() ; 
		return c ; 
	}
	
	
	
	//Presence
	
	

	@Override
	public Presence addPresence(Presence p) {
		em.persist(p); 
		em.flush();
		return p ; 
	}

	@Override
	public Presence getPresence(long id) {
		Presence p = em.find(Presence.class, id) ; 
		if(p==null) new RuntimeException() ; 
		return p ; 
	}

	@Override
	public void updatePresence(Presence p) {
		em.merge(p) ; 
		
	}

	@Override
	public void deletePresence(Presence p) {
		p = em.merge(p);  
		em.remove(p);
	}

	@Override
	public List<Presence> getAllPresence() {
		Query q = em.createQuery("Select p from Presence") ; 
		return q.getResultList() ; 
	}

	@Override
	public List<Presence> getAllEnfantPerEnfant(Enfant e) {
		Query req = em.createQuery("select p from p a where p.enfant = :enfant",Presence.class).setParameter("enfant", e)  ; 
		try {
			return req.getResultList() ; 
			
		}catch(NoResultException exception) {
			System.out.println(exception);
			return null ; 
			
		}
	}
	
	
	
	//Statistique
	
	@Override
	public int getNombreDesEnfants() {
	        // Assuming "Enfant" is the name of your entity class
	        Query query = em.createQuery("SELECT COUNT(e) FROM Enfant e");
	        Long count = (Long) query.getSingleResult();
	        return count.intValue();
	    }

	@Override
	public int getNombreDesAssistantes() {
		  Query query = em.createQuery("SELECT COUNT(a) FROM Assistante a");
	        Long count = (Long) query.getSingleResult();
	        return count.intValue();
	}

	@Override
	public int getNombreDesContratActifs() {
		  Query query = em.createQuery("SELECT COUNT(c) FROM Contract c  where c.etat = true");
	        Long count = (Long) query.getSingleResult();
	        return count.intValue();
	}



	
 
}

package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ejb.dao.Enfant;
import ejb.dao.jpa.IGardeMaternellLocal;

@Stateless
@WebService
public class GardeMaternelleService {
	
		@EJB
		private IGardeMaternellLocal metier ; 
		
		@WebMethod
		public Enfant getEnfant(@WebParam(name = "enfant_id")long id) {
			return metier.getEnfant(id);
		}
		
		@WebMethod
		public List<Enfant> getAllEnfant() {
			return metier.getAllEnfant();
		}

		@WebMethod
		public void addEnfant(@WebParam(name = "enfant")Enfant en) {
			metier.addEnfant(en) ;
			
		}
}

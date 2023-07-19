package web.converters;


import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ejb.dao.Assistante;
import ejb.dao.Enfant;
import ejb.dao.jpa.GardeMaternelleImpl;
import ejb.dao.jpa.IGardeMaternellLocal;


@FacesConverter(forClass=Assistante.class)
public class MyConverter implements Converter {
	 @Override
	    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String value) {        	    
           if ( value != null && value.trim().length() > 0 ) {
                try {
                	IGardeMaternellLocal metier = new GardeMaternelleImpl() ; 
                    Assistante cl= metier.getAssistante( Integer.parseInt( value ) );
                    return cl;
                } catch ( Exception e ) {
                  
                    return null;
                }
            }
            else {
                return null;
            }
            
	    }

	    @Override
	    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
	      
	    	if (object != null)
	    	{	 
	           return  String.valueOf(  ((Assistante) object).getId());
	    	}
	    	else
	    		return null;
	    }
  
}
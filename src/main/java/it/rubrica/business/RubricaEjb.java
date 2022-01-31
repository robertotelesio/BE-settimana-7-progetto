package it.rubrica.business;



import java.util.List;


import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * Session Bean implementation class RubricaEjb
 */
@Stateless
@LocalBean
public class RubricaEjb implements RubricaEjbLocal {
	@PersistenceContext(unitName="rubricaPS")
	EntityManager em;
   
    public RubricaEjb() {
      
    }
    public Contatto insertContatto(Contatto cont) {
      	 em.persist(cont);
		return cont;
       }
    public Contatto insertContatto(String nome, String cognome, String email, String numTelefono) {
    	Contatto c = new Contatto(nome, cognome, email);
    	return insertContatto(c); 		
    }
    public List<Object[]> getAllContattiEnumeri() {
    	Query q = em.createQuery("SELECT distinct c.nome, c.cognome, c.email, n.numTelefono FROM Contatto c JOIN NumTelefono n ON "
    			+ " c.contatto = n.contatto"
    			+ "  WHERE NOT n.numTelefono =''");
    	List<Object[]> contatti = q.getResultList();
    	return contatti;
    }
    public List<Contatto> getContattoByCognome(String cognome) {
    	Query q = em.createNamedQuery("getContatto.byCognome");
    	q.setParameter("cognome", "%" + cognome + "%");
    	List<Contatto> contatti = q.getResultList();
    	for(Contatto i : contatti) {
    		System.out.println(i.getNome() +" " + i.getCognome());
    	}
    	return contatti;
    }
    public List<Contatto> getContattoByNumero(String numero) {
    	Query q = em.createNamedQuery("getContatto.byNumero");
    	q.setParameter("numero", "%" + numero + "%");
    	List<Contatto> contatti = q.getResultList();
    	for(Contatto i : contatti) {
    		System.out.println(i.getNome() +" " + i.getCognome());
    	}
    	return contatti;
    }

    public void aggiornaContattoEsistente(int id, String numero1, String numero2, String nome, String cognome, String email) {
    	Contatto c = em.find(Contatto.class, id);
    	if(c== null) {
    		c = new Contatto();
    		c.setId(id);
    	}
    	c.setNome(nome);
    	c.setCognome(cognome);
    	c.setEmail(email);
    
    	if(!numero1.isBlank()) {
    		NumTelefono n1= new NumTelefono();
    		n1.setContatto(c);
    		n1.setNumTelefono(numero1);
    		c.getNumTelefoni().add(n1);
    	}
    	if(!numero2.isBlank()) {
    		NumTelefono n2= new NumTelefono();
    		n2.setContatto(c);
    		n2.setNumTelefono(numero2);
    		c.getNumTelefoni().add(n2);
    	}
    	em.merge(c);
    }
    
    public void deleteContatto(Contatto c) {
    	em.remove(em.find(Contatto.class, c.getId()));
    }
   
}

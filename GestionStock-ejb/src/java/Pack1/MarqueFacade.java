/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack1;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class MarqueFacade extends AbstractFacade<Marque> {

    @EJB
    private ProduitFacade produitFacade;

    @PersistenceContext(unitName = "GestionStock-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarqueFacade() {
        super(Marque.class);
    }
    
    public boolean creation(String nom, String paysOrigine){
        if(this.find(nom) == null){
            Marque m = new Marque(nom, paysOrigine);
            em.persist(m);
            return true;
        }
        return false;
    }
    
    public boolean supprission(String nom){
        Marque m = this.find(nom);
        if(m != null){
            List<Produit> prds = m.getListeProduits();
            for(Produit p: prds){
                produitFacade.suppressionProduit(p.getId());
            }
            em.remove(m);
            return true;
        }
        return false;
    }
    
    public List<Produit> findProduiByMarque(String nomMarque){
        return em.createQuery("SELECT p FROM Produit p WHERE p.marqueProduit.id = :nomMarque", Produit.class).setParameter("nomMarque", nomMarque).getResultList();
    }
    
    public boolean modifierMarque(String nomMarque, String origineMarque){
        Marque m = this.find(nomMarque);
        if(m != null){
            m.setOrigine(origineMarque);
            em.merge(m);
            return true;
        }
        return false;
    }
    
    public boolean AjouterProduit(String nomMarque, Produit p){
        Marque m = this.find(nomMarque);
        if(m != null){
            List<Produit> list = m.getListeProduits();
            list.add(p);
            m.setListeProduits(list);
            em.merge(m);
            return true;
        }
        return false;
    }
    
    public boolean supprimerProduit(String nomMarque, Produit p){
        Marque m = this.find(nomMarque);
        if(m != null){
            List<Produit> list = m.getListeProduits();
            list.remove(p);
            m.setListeProduits(list);
            em.merge(m);
            return true;
        }
        return false;
    }
    
    public List<Produit> listing(String nomMarque){
        Marque m = this.find(nomMarque);
        return m.getListeProduits();
    }
}

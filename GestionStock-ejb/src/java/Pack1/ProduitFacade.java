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
public class ProduitFacade extends AbstractFacade<Produit> {

    @EJB
    private StockFacade stockFacade;
    @EJB
    private MarqueFacade marqueFacade;
    @PersistenceContext(unitName = "GestionStock-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
    
    public boolean creationProduit(String refProduit, String marqueProduit, String denomination, float prix, float poids, float volume)
    {
        if(this.find(refProduit) == null){
            Produit p = new Produit(refProduit, marqueFacade.find(marqueProduit), denomination, prix, poids, volume);
            //marqueFacade.AjouterProduit(marqueProduit, p);
            em.persist(p);   
            return true;
        }
        
        return false;
    }
    
    public boolean suppressionProduit(String refProduit)
    {
        Produit p = this.find(refProduit);
        if( p != null){
            List<Stock> stocks = stockFacade.findAll();
            stocks.forEach((s) -> {
                stockFacade.supprimerElementStock(s.getId(), refProduit);
            });
            em.remove(p);
            return true;
        }
        return false;
    }
    
    public boolean modifierProduit(String refProduit, String marqueProduit, String denomination, float prix, float poids, float volume){
        Marque m = marqueFacade.find(marqueProduit);
        if(this.find(refProduit) != null && m != null){
            Produit p = this.find(refProduit);
            p.setMarqueProd(m);
            p.setDenomination(denomination);
            p.setPoids(poids);
            p.setPrix(prix);
            p.setVolume(volume);
            em.merge(p);
            return true;
        }
        
        return false;
    }
    
    public boolean modifierMarqueProduit(String refProduit, String marqueProduit){
        Marque m = marqueFacade.find(marqueProduit);
        if(this.find(refProduit) != null && m != null){
            Produit p = this.find(refProduit);
            p.setMarqueProd(m);
            em.merge(p);
            return true;
        }
        return false;
    }
    
    public boolean modifierDemoninationProduit(String refProduit, String denomination){
        if(this.find(refProduit) != null){
            Produit p = this.find(refProduit);
            p.setDenomination(denomination);
            em.merge(p);
            return true;
        }
        return false;
    }
    
    public boolean modifierPrixProduit(String refProduit, float prix){
        if(this.find(refProduit) != null){
            Produit p = this.find(refProduit);
            p.setPrix(prix);
            em.merge(p);
            return true;
        } 
        return false;
    }
    
    public boolean modifierVolumeProduit(String refProduit, float volume){
        if(this.find(refProduit) != null){
            Produit p = this.find(refProduit);
            p.setVolume(volume);
            em.merge(p);
            return true;
        } 
        return false;
    }
    
    public boolean modifierPoidsProduit(String refProduit, float poids){
        if(this.find(refProduit) != null){
            Produit p = this.find(refProduit);
            p.setPoids(poids);
            em.merge(p);
            return true;
        } 
        return false;
    }
    
    public List<Produit> listingProduits()
    {
        return this.findAll();
    }
    
    public boolean estDansUnStock(String refProduit){
        List<Stock> stocks = stockFacade.findAll();
        return stocks.stream().map((s) -> s.getListeDeStock()).map((lds) -> lds.getElementsDeStock()).anyMatch((les) -> (les.stream().anyMatch((e) -> (refProduit.equals(e.getRefProduit())))));
    }
    
    public int totalProduit(String refProduit){
        int total = 0;
        List<Stock> stocks = stockFacade.findAll();
        for(Stock s: stocks){
            ListeDeStock lds = s.getListeDeStock();
            List<ElementDeStock> les = lds.getElementsDeStock();
            total = les.stream().filter((e) -> (refProduit.equals(e.getRefProduit()))).map((e) -> e.getQuantite()).reduce(total, Integer::sum);
        }
        
        return total;
    }
}

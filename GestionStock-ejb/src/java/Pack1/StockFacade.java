/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack1;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "GestionStock-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }
    
    public boolean creationStock(String nomDeStock){
        Stock s =this.find(nomDeStock);
        if(s == null ){
            Stock stock = new Stock(nomDeStock);
            em.persist(stock);
            return true;
        }
        return false;
    }
    
    public boolean supprissionStock(String nomDeStock){
        Stock s =this.find(nomDeStock);
        if(s != null ){
            Stock stock = new Stock(nomDeStock);
            em.remove(stock);
            return true;
        }
        return false;
    }
    
    public boolean sortieProduitStock(String nomDeStock, String refProduit, int quantite){
        Stock stock = this.find(nomDeStock);
        if( stock != null){
            ListeDeStock lds = stock.getListeDeStock();
            List<ElementDeStock> listElements = lds.getElementsDeStock();
            ElementDeStock element = new ElementDeStock();
            for(ElementDeStock e: listElements){
                if(e.getRefProduit().equals(refProduit)){
                    element = e;
                    listElements.remove(e);
                    break;
                }
            }
            
            if(element.getQuantite() < quantite) return false;
            
            element.setQuantite(element.getQuantite() - quantite);
            listElements.add(element);
            lds.setElementsDeStock(listElements);
            stock.setListeDeStock(lds);
            em.merge(stock);
            return true;
        }
        return false;
    }
    
    public boolean entreeProduitStock(String nomDeStock, String refProduit, int quantite){
        Stock stock = this.find(nomDeStock);
        ListeDeStock lds = stock.getListeDeStock();
        List<ElementDeStock> listElements = lds.getElementsDeStock();
        ElementDeStock element = new ElementDeStock();
        boolean done = false;
        for(ElementDeStock e: listElements){
            if(e.getRefProduit().equals(refProduit)){
                element.setQuantite(e.getQuantite() + quantite);
                element.setRefProduit(refProduit);
                listElements.remove(e);
                listElements.add(element);
                done = true;
                break;
            }
        }
        if(!done){
            element.setQuantite(quantite);
            element.setRefProduit(refProduit);
            listElements.add(element);
        }
        lds.setElementsDeStock(listElements);
        stock.setListeDeStock(lds);
        em.merge(stock);
        return true;
    }
    
    public boolean modifierProduitStock(String nomDeStock, String refProduit, int quantite){
        Stock stock = this.find(nomDeStock);
        if( stock != null){
            ListeDeStock lds = stock.getListeDeStock();
            List<ElementDeStock> listElements = lds.getElementsDeStock();
            ElementDeStock element = new ElementDeStock();
            for(ElementDeStock e: listElements){
                if(e.getRefProduit().equals(refProduit)){
                    element = e;
                    listElements.remove(e);
                    break;
                }
            }
            element.setQuantite(quantite);
            listElements.add(element);
            lds.setElementsDeStock(listElements);
            stock.setListeDeStock(lds);
            em.merge(stock);
            return true;
        }
        return false;
    }
    
    public ListeDeStock listingDeStocks(){
        ListeDeStock lds = new ListeDeStock();
        List<ElementDeStock> lesks = new ArrayList<>();
        List<Stock> stocks = this.findAll();
        
        stocks.stream().map((s) -> s.getListeDeStock()).map((listeDeStock) -> listeDeStock.getElementsDeStock()).forEachOrdered((les) -> {
            les.forEach((e) -> {
                lesks.add(e);
            });
        });
        
        lds.setElementsDeStock(lesks);
        return lds;
    }
    
    public ListeDeStock listingDeStock(String nomStock){
        Stock stock = this.find(nomStock);
        return stock.getListeDeStock();
    }

    public boolean supprimerElementStock(String nomStock, String refProduit) {
        Stock stock = this.find(nomStock);
        
        if( stock != null){
            ListeDeStock lds = stock.getListeDeStock();
            List<ElementDeStock> listElements = lds.getElementsDeStock();
            for(ElementDeStock e: listElements){
                String refElment = e.getRefProduit();
                if(refElment.equals(refProduit)){
                    listElements.remove(e);
                    break;
                }
            }
            lds.setElementsDeStock(listElements);
            
            em.remove(stock);
            stock.setListeDeStock(lds);
            em.persist(stock);
            return true;
        }
        return false;
    }
    
    public int quantiteProduit(String refProduit){
        int quantite = 0;
        List<Stock> stocks = this.findAll();
        for(Stock s: stocks){
            ListeDeStock lds = s.getListeDeStock();
            List<ElementDeStock> les = lds.getElementsDeStock();
            for(ElementDeStock e: les){
                if(e.getRefProduit() == refProduit){
                    quantite += e.getQuantite();
                }
            }
        }
        return quantite;
    }
    
     public List<ElementDeStock> findProductsByStock(String stockId) {
        Stock stock = em.find(Stock.class, stockId);
        if (stock != null) {
            return stock.getListeDeStock().getElementsDeStock();
        }
        return null;
    }
}

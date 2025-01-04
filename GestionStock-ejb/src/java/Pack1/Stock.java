/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack1;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author hp
 */
@Entity
public class Stock implements Serializable {

    @Id
    private String id;
    
    private ListeDeStock listeDeStock;

    public Stock() {
        
    }

    public Stock(String NomDeStock, ListeDeStock listeDeStock) {
        this.id = NomDeStock;
        this.listeDeStock = listeDeStock;
    }

    public Stock(String nomDeStock) {
        this.id = nomDeStock;
        this.listeDeStock = new ListeDeStock();
    }

    public ListeDeStock getListeDeStock() {
        return listeDeStock;
    }

    public void setListeDeStock(ListeDeStock listeDeStock) {
        this.listeDeStock = listeDeStock;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pack1.Stock[ id=" + id + " ]";
    }
    
}

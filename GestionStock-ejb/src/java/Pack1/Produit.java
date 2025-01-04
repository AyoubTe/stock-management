/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack1;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author hp
 */
@Entity
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    
    @ManyToOne
    private Marque marqueProduit;
    
    private String denomination;
    
    private float prix;
    
    private float poids;
    
    private float volume;
    
    
    
    public Produit()
    {
        super();
    }

    public Produit(String refProduit, Marque marqueProduit, String denomination, float prix, float poids, float volume) {
        this.id = refProduit;
        this.marqueProduit = marqueProduit;
        this.denomination = denomination;
        this.prix = prix;
        this.poids = poids;
        this.volume = volume;
    }

    
    /**
     * Get the value of volume
     *
     * @return the value of volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Set the value of volume
     *
     * @param volume new value of volume
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }


    /**
     * Get the value of poids
     *
     * @return the value of poids
     */
    public float getPoids() {
        return poids;
    }

    /**
     * Set the value of poids
     *
     * @param poids new value of poids
     */
    public void setPoids(float poids) {
        this.poids = poids;
    }

    

    /**
     * Get the value of prix
     *
     * @return the value of prix
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Set the value of prix
     *
     * @param prix new value of prix
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }


    /**
     * Get the value of denomination
     *
     * @return the value of denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Set the value of denomination
     *
     * @param denomination new value of denomination
     */
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }


    /**
     * Get the value of marqueProd
     *
     * @return the value of marqueProd
     */
    public Marque getMarqueProd() {
        return marqueProduit;
    }

    /**
     * Set the value of marqueProd
     *
     * @param marqueProd new value of marqueProd
     */
    public void setMarqueProd(Marque marqueProd) {
        this.marqueProduit = marqueProd;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pack1.Produit[ id=" + id + " ]";
    }
    
}

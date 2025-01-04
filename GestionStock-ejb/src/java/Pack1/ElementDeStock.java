package Pack1;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class ElementDeStock implements Serializable {

    private String RefProduit;
    private int Quantite;

    public ElementDeStock(String RefProduit, int Quantite) {
        this.RefProduit = RefProduit;
        this.Quantite = Quantite;
    }

    public ElementDeStock() {
    }
    
    /**
     * Get the value of Quantite
     *
     * @return the value of Quantite
     */
    public int getQuantite() {
        return Quantite;
    }

    /**
     * Set the value of Quantite
     *
     * @param Quantite new value of Quantite
     */
    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }


    public String getRefProduit() {
        return RefProduit;
    }

    public void setRefProduit(String RefProduit) {
        this.RefProduit = RefProduit;
    }
}

package Pack1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */

public class ListeDeStock implements Serializable {

    private List<ElementDeStock> elementsDeStock;

    public ListeDeStock() {
        elementsDeStock = new ArrayList<>();
    }

    public ListeDeStock(List<ElementDeStock> elementDeStocks) {
        this.elementsDeStock = elementDeStocks;
    }

    public List<ElementDeStock> getElementsDeStock() {
        return elementsDeStock;
    }

    public void setElementsDeStock(List<ElementDeStock> elementDeStocks) {
        this.elementsDeStock = elementDeStocks;
    }
}

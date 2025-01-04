package Pack1;

import Pack1.Marque;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-29T17:39:21")
@StaticMetamodel(Produit.class)
public class Produit_ { 

    public static volatile SingularAttribute<Produit, Float> volume;
    public static volatile SingularAttribute<Produit, Float> prix;
    public static volatile SingularAttribute<Produit, Float> poids;
    public static volatile SingularAttribute<Produit, Marque> marqueProduit;
    public static volatile SingularAttribute<Produit, String> id;
    public static volatile SingularAttribute<Produit, String> denomination;

}
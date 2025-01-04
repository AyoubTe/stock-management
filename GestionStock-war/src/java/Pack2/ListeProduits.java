package Pack2;

import Pack1.Produit;
import Pack1.ProduitFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeProduits extends HttpServlet {

    @EJB
    private ProduitFacade produitFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListeProduits</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeProduits at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>La liste des produits</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("<style>");
            out.println("body {");
            out.println("display: flex;");
            out.println("justify-content: center;");
            out.println("align-items: center;");
            out.println("width: 100%;");
            out.println("height: 100vh;");
            out.println("flex-direction: column;");
            out.println("background: #f4f4f4;");
            out.println("font-family: Arial, sans-serif;");
            out.println("}");
            out.println("h1 {");
            out.println("color: #333;");
            out.println("}");
            out.println("table {");
            out.println("border-collapse: collapse;");
            out.println("width: 90%;");
            out.println("margin: 10px 0;");
            out.println("}");
            out.println("th, td {");
            out.println("border: 1px solid #ddd;");
            out.println("text-align: center;");
            out.println("}");
            out.println("th {");
            out.println("padding: 8px;");
            out.println("background-color: #4CAF50;");
            out.println("color: white;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("background-color: #f2f2f2;");
            out.println("}");
            out.println("tr:hover {");
            out.println("background-color: #ddd;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>La liste des produits</h1>");
            
            List<Produit> produits = produitFacade.findAll();
            out.println("<table class=\"table table-striped\">");
            out.println("<tr><th>Réference du produit</th><th>Dénomination du produit</th><th>Prix du produit</th><th>Poids du produit</th><th>Volume du produit</th><th>Marque du produit</th><th>Quantité du produit</th><th>Actions</th></tr>");
            for(Produit p: produits){
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getDenomination() + "</td>");
                out.println("<td>" + p.getPrix() + "</td>");
                out.println("<td>" + p.getPoids() + "</td>");
                out.println("<td>" + p.getVolume() + "</td>");
                out.println("<td>" + p.getMarqueProd().getId() + "</td>");
                out.println("<td>" + produitFacade.totalProduit(p.getId()) + "</td>");
                out.println("<td>");
                out.println("<a href=\"http://localhost:8080/GestionStock-war/ModifierProduit?produit=" + p.getId() + "\" class=\"btn btn-success btn-sm\">Modifier</a>");
                out.println("<a href=\"http://localhost:8080/GestionStock-war/SupprimerProduit?produit=" + p.getId() + "\" class=\"btn btn-danger btn-sm\">Supprimer</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

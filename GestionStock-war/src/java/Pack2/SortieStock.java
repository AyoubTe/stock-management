package Pack2;

import Pack1.ElementDeStock;
import Pack1.ProduitFacade;
import Pack1.Stock;
import Pack1.StockFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortieStock extends HttpServlet {

    @EJB
    private ProduitFacade produitFacade;

    @EJB
    private StockFacade stockFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stockId = request.getParameter("stockId");
        if (stockId != null) {
            Stock stock = stockFacade.find(stockId);
            if (stock != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                for (ElementDeStock element : stock.getListeDeStock().getElementsDeStock()) {
                    jsonArrayBuilder.add(Json.createObjectBuilder()
                            .add("refProduit", element.getRefProduit())
                            .add("quantite", element.getQuantite()));
                }
                out.print(jsonArrayBuilder.build().toString());
                out.flush();
                return;
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SortieStock</title>");            
            out.println("<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("form { width: 50%; height: 80vh; display: flex; flex-direction: column; align-items: center; }");
            out.println("input, select { width: 80%; padding: 10px; height: 8%; margin: 5px; border-radius: 5px; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            out.println("<h2>Une sortie du stock: </h2>");
            
            out.println("<form method =\"POST\">");
            out.println("Nom du stock: ");
            out.println("<select name = \"NomStock\" id=\"NomStock\">");
            List<Stock> stock = stockFacade.findAll();
            out.println("<option value=\"\">Séléctionner stock</option>");
            for (Stock s : stock) {
                out.println("<option value=\"" + s.getId() + "\">" + s.getId() + "</option>");
            }
            out.println("</select>");
            
            out.println("</br>");
            out.println("Nom produit: ");
            out.println("<select name = \"NomProduit\" id=\"NomProduit\">");
            out.println("</select>");
            
            out.println("</br>");
            out.println("Quantité: ");
            out.println("<input type=\"number\" name=\"Quantite\" required/>");
            out.println("</br>");
            out.println("<button  type = \"submit\">Sortir commande</button>");
            
            out.println("</form>");
            
            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println("$('#NomStock').change(function() {");
            out.println("var stockId = $(this).val();");
            out.println("$.ajax({");
            out.println("url: 'SortieStock?stockId=' + stockId,");
            out.println("method: 'GET',");
            out.println("success: function(data) {");
            out.println("var produitSelect = $('#NomProduit');");
            out.println("produitSelect.empty();");
            out.println("for (var i = 0; i < data.length; i++) {");
            out.println("produitSelect.append('<option value=\"' + data[i].refProduit + '\">' + data[i].refProduit + '</option>');");
            out.println("}");
            out.println("},");
            out.println("error: function(xhr, status, error) {");
            out.println("console.error('Erreur lors de la requête AJAX: ', status, error);");
            out.println("}");
            out.println("});");
            out.println("});");
            out.println("});");
            out.println("</script>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SortieStock</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            
            String nomStock = request.getParameter("NomStock");
            String nomProduit = request.getParameter("NomProduit");
            int quantite = Integer.parseInt(request.getParameter("Quantite"));
            
            boolean added = stockFacade.sortieProduitStock(nomStock, nomProduit, quantite);
            if (added) {
                out.println("<div class='alert alert-success'>La quantité du produit a été retiré avec succès!!</div>");
            } else {
                out.println("<div class='alert alert-danger'>Une erreur est survenue lors de la sortie de la quantité du produit!!</div>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

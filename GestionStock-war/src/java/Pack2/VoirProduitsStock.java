package Pack2;

import Pack1.ElementDeStock;
import Pack1.ListeDeStock;
import Pack1.Stock;
import Pack1.StockFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class VoirProduitsStock extends HttpServlet {

    @EJB
    private StockFacade stockFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VoirProduitsStock</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoirProduitsStock at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Consulter Stock</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("<style>");
            out.println("body {");
            out.println("display: flex;");
            out.println("justify-content: center;");
            out.println("align-items: center;");
            out.println("width: 100%;");
            out.println("height: 100vh;");
            out.println("flex-direction: column;");
            out.println("overflow: hidden;");
            out.println("background: #f4f4f4;");
            out.println("font-family: Arial, sans-serif;");
            out.println("}");
            out.println("h2 {");
            out.println("color: #333;");
            out.println("margin-bottom: 30px;");
            out.println("}");
            out.println(".container { margin-top: 50px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h2>La liste des stocks: </h2>");

            List<Stock> stocks = stockFacade.findAll();
            if (stocks.size() > 0) {
                int nb = 1;
                for (Stock s : stocks) {
                    out.println("<div class=\"card mb-3\">");
                    out.println("<div class=\"card-header\">");
                    out.println("Le stock " + nb + " : " + s.getId());
                    out.println("</div>");
                    out.println("<div class=\"card-body\">");
                    ListeDeStock lds = s.getListeDeStock();
                    List<ElementDeStock> les = lds.getElementsDeStock();
                    out.println("<table class=\"table table-striped\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th scope=\"col\">Référence Produit</th>");
                    out.println("<th scope=\"col\">Quantité</th>");
                    out.println("<th scope=\"col\">Actions</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (ElementDeStock e : les) {
                        out.println("<tr>");
                        out.println("<td>" + e.getRefProduit() + "</td>");
                        out.println("<td>" + e.getQuantite() + "</td>");
                        out.println("<td><a href=\"http://localhost:8080/GestionStock-war/EntreeStock\" class=\"btn btn-success btn-sm\">Entree</a><a href=\"http://localhost:8080/GestionStock-war/SortieStock\" class=\"btn btn-primary btn-sm\">Sortie</a><a href=\"http://localhost:8080/GestionStock-war/RetirerStock\" class=\"btn btn-danger btn-sm\">Retirer</a></td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("</div>");
                    out.println("</div>");
                    nb++;
                }
            } else {
                out.println("<div class=\"alert alert-warning\">Aucun stock n'est créé</div>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

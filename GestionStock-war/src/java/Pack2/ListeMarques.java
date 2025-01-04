package Pack2;

import Pack1.Marque;
import Pack1.MarqueFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListeMarques extends HttpServlet {

    @EJB
    private MarqueFacade marqueFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListeMarques</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeMarques at " + request.getContextPath() + "</h1>");
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
            out.println("<title>La liste des marques</title>");
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
            out.println("}");
            out.println("table {");
            out.println("border-collapse: collapse;");
            out.println("width: 80%;");
            out.println("margin: 20px 0;");
            out.println("}");
            out.println("th, td {");
            out.println("border: 1px solid #ddd;");
            out.println("padding: 8px;");
            out.println("text-align: center;");
            out.println("}");
            out.println("th {");
            out.println("background-color: #4CAF50;");
            out.println("color: white;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("background-color: #f2f2f2;");
            out.println("}");
            out.println("tr:hover {");
            out.println("background-color: #ddd;");
            out.println("}");
            out.println("button {");
            out.println("background: #4CAF50;");
            out.println("color: white;");
            out.println("padding: 10px 20px;");
            out.println("border: none;");
            out.println("border-radius: 5px;");
            out.println("cursor: pointer;");
            out.println("margin: 0 5px;");
            out.println("}");
            out.println("button:hover {");
            out.println("background: #45a049;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>La liste des marques</h2>");
            
            List<Marque> marques = marqueFacade.findAll();
            out.println("<table>");
            out.println("<tr><th>Nom de marque</th><th>Pays de marque</th><th>Actions</th></tr>");
            for(Marque m: marques){
                out.println("<tr>");
                out.println("<td>" + m.getId() + "</td>");
                out.println("<td>" + m.getOrigine() + "</td>");
                out.println("<td>");
                out.println("<a href=\"http://localhost:8080/GestionStock-war/ListeProduitsMarque?marque=" + m.getId() + "\"><button>Produits</button></a>");
                out.println("<a href=\"http://localhost:8080/GestionStock-war/ModifierMarque?marque=" + m.getId() + "\"><button>Modifier</button></a>");
                out.println("<a href=\"http://localhost:8080/GestionStock-war/SuppressionMarque?marque=" + m.getId() + "\"><button>Supprimer</button></a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e) {
            
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

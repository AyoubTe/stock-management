/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

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
public class PanelDeGestion extends HttpServlet {

    @EJB
    private StockFacade stockFacade;

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Panel de gestion</title>");
            out.println("<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />");
            out.println("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"all\" />");
            out.println("</head>");
            out.println("<body>");
            
            // Header
            out.println("<div id=\"header\">");
            out.println("<div class=\"shell\">");
            out.println("<div id=\"top\">");
            out.println("<h1><a href=\"#\">Gestion de stock</a></h1>");
            out.println("<div id=\"top-navigation\">");
            out.println("Bienvenue <a href=\"#\"><strong>Administrator</strong></a> <span>|</span> <a href=\"#\">Aide</a> <span>|</span> <a href=\"#\">Les paramètres du profil</a> <span>|</span> <a href=\"#\">Déconnection</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            // End Header
            
            // Container
            out.println("<div id=\"container\">");
            out.println("<div class=\"shell\">");
            
            // Small Nav
            out.println("<div class=\"small-nav\"> <a href=\"http://localhost:8080/GestionStock-war/PanelDeGestion\">Panel de gestion</a> <span>&gt;</span> Les arciles actuelles </div>");
            // End Small Nav
            
            // Main
            out.println("<div id=\"main\">");
            out.println("<div class=\"cl\">&nbsp;</div>");
            
            // Content
            out.println("<div id=\"content\">");
            
            // Box
            out.println("<div class=\"box\">");
            
            // Box Head
            out.println("<div class=\"box-head\">");
            out.println("<h2 class=\"left\">La liste des stocks</h2>");
            out.println("<div class=\"right\">");
            out.println("<label>Chercher un stock</label>");
            out.println("<input type=\"text\" class=\"field small-field\" />");
            out.println("<input type=\"submit\" class=\"button\" value=\"rechercher\" />");
            out.println("</div>");
            out.println("</div>");
            // End Box Head
            
            // Table
            out.println("<div class=\"table\">");
            out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<tr>");
            out.println("<th width=\"13\"><input type=\"checkbox\" class=\"checkbox\" /></th>");
            out.println("<th>Nom</th>");
            out.println("<th width=\"110\" class=\"ac\">Gestion des articles</th>");
            out.println("</tr>");
            List<Stock> stocks = stockFacade.findAll();
            // Exemple de ligne de stock (à remplacer par une boucle pour récupérer les stocks réels)
            out.println("<tr class=\"odd\">");
            out.println("<td><input type=\"checkbox\" class=\"checkbox\" /></td>");
            out.println("<td><h3><a href=\"#\">Nom du stock</a></h3></td>");
            out.println("<td><a href=\"#\" class=\"ico del\">Supprimer</a><a href=\"#\" class=\"ico edit\">Modifier</a></td>");
            out.println("</tr>");
            
            out.println("</table>");
            
            // Pagging
            out.println("<div class=\"pagging\">");
            out.println("<div class=\"left\">Affichage 1-12 of 44</div>");
            out.println("<div class=\"right\"> <a href=\"#\">Précedant</a> <a href=\"#\">1</a> <a href=\"#\">2</a> <a href=\"#\">3</a> <a href=\"#\">4</a> <a href=\"#\">245</a> <span>...</span> <a href=\"#\">Next</a> <a href=\"#\">View all</a> </div>");
            out.println("</div>");
            // End Pagging
            
            out.println("</div>");
            // End Table
            
            out.println("</div>");
            // End Box
            
            out.println("</div>");
            // End Content
            
            out.println("</div>");
            // End Main
            
            out.println("</div>");
            // End Shell
            
            out.println("</div>");
            // End Container
            
            out.println("</body>");
            out.println("</html>");
        } catch(IOException e){
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traiter les données POST si nécessaire
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

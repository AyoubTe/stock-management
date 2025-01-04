/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.MarqueFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class CreationMarque extends HttpServlet {

    @EJB
    private MarqueFacade marqueFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); // out.println("");
            out.println("<title>Servlet CreationMarque</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Creation Marque</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
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
            out.println("form {");
            out.println("width: 50%;");
            out.println("height: 80vh;");
            out.println("display: flex;");
            out.println("flex-direction: column;");
            out.println("align-items: center;");
            out.println("}");
            out.println("input,");
            out.println("select {");
            out.println("width: 80%;");
            out.println("padding: 10px;");
            out.println("height: 8%;");
            out.println("margin: 5px;");
            out.println("border-radius: 5px;");
            out.println("}");
            out.println("button {");
            out.println("background: black;");
            out.println("color: white;");
            out.println("padding: 10px 20px;");
            out.println("border-radius: 5px;");
            out.println("}");
            out.println("</style>");

            out.println("<body>");
            out.println("<h2>Création d'une marque: </h2>");
            
            out.println("<form  method = \"POST\">");
            out.println("Nom de la marque :");    
            out.println("<input type = \"text\" name = \"Nom\" placeholder=\"Nom de la marque\" required/>");    
            out.println("</br>");    
            out.println("Pays d'origine :");    
            out.println("<input type = \"text\" name = \"PaysOrigine\" placeholder = \"Pays d'origine\" required/>");    
            out.println("</br>");    
            out.println("<button type = \"submit\">Créer la marque</button>");    
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); // out.println(""); 
            out.println("<title>Creation avec succes</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            String nomMarque = request.getParameter("Nom");
            String paysOrigine = request.getParameter("PaysOrigine");
            
            boolean created = marqueFacade.creation(nomMarque, paysOrigine);
            
            if( created == true){
                out.println("<div class='alert alert-success'>Creation de la marque avec succes !!</div>");
            } else {
                out.println("<div class='alert alert-danger'>Erreur la marque existe dèjà !!</div>");
            }
            out.println("</br>");
            out.println("<a href=\"http://localhost:8080/GestionStock-war/CreationMarque\"><button>Retour</button></a>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

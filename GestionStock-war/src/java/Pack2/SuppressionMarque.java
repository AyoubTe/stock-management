/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.Marque;
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
public class SuppressionMarque extends HttpServlet {

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
            out.println("<head>");
            out.println("<title>Servlet SuppressionMarque</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SuppressionMarque at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Supprimer une marque</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");            
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("form { width: 50%; height: 80vh; display: flex; flex-direction: column; align-items: center; }");
            out.println("input, select { width: 80%; padding: 10px; height: 8%; margin: 5px; border-radius: 5px; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            out.println("<h2>La suppression d'une marque</h2>");
            
            String nomMarqe = request.getParameter("marque");
            Marque marque = marqueFacade.find(nomMarqe);
            
            out.println("<form  method = \"POST\">"); 
            out.println("<p style=\"color:red;\">Attention vous allez supprimer la marque suivante:</br>"
                    + "Cela entraine la suppression de tous les produits de cette marque!!</br>"
                    + "Aussi tous les élèments de stock de ses produits!!</br>"
                    + "S'il vous plait lire attentvement </p></br></br></hr>");
            out.println("Le nom de la marque: ");
            out.println("<input type = \"text\" value = \""+marque.getId()+"\" name=\"NomMarque\" readonly/>");
            out.println("</br>");
            out.println("L'origine de la marque: ");
            out.println("<input type = \"text\" value = \""+marque.getOrigine()+"\" readonly/>");
            out.println("</br>");
            out.println("<button type = \"submit\">Supprimer</button>");
            
            out.println("</form>");
            out.println("<a href=\"http://localhost:8080/GestionStock-war/ListeMarques\"><button>Annuler</button></a>");
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
            out.println("<head>");
            out.println("<title>Supprimer Marque</title>"); 
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
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            
            String nomMarque = request.getParameter("marque");
            
            boolean deleted = marqueFacade.supprission(nomMarque);
            
            if(deleted){
                out.println("<div class='alert alert-success'>Le marque a été supprimée avec succès</div>");
            } else{
                out.println("<div class='alert alert-danger'>Une erreur se produite lors de la supprission</div>");
            }
            
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
    }// </editor-fold>

}

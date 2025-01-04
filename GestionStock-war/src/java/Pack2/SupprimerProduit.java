/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.MarqueFacade;
import Pack1.Produit;
import Pack1.ProduitFacade;
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
public class SupprimerProduit extends HttpServlet {

    @EJB
    private MarqueFacade marqueFacade;


    @EJB
    private ProduitFacade produitFacade;

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
            out.println("<title>Servlet SupprimerProduit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SupprimerProduit at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Supprimer un produit</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("form { width: 50%; height: 40vh; display: flex; flex-direction: column; align-items: center; }");
            out.println("input, select { width: 80%; padding: 10px; height: 8%; margin: 5px; border-radius: 5px; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            out.println("<h2>La supprission d'un produit: </h2>");
            out.println("<p style=\"color:red;\">Attention vous allez supprimer le produit suivant:</br>Cela va entrainer la supprission de produit et tous ses éléments de stock!!<p>");
            String nomProduit = request.getParameter("produit");
            Produit p = produitFacade.find(nomProduit);
            
            out.println("<form  method = \"POST\">");
            out.println("Reference du produit :");    
            out.println("<input type = \"text\" name = \"RefProduit\" value = \""+p.getId()+"\" readonly/>"); 
            out.println("</br>"); 
            out.println("Dénomination :");
            out.println("<input type = \"text\" name = \"DenominationProduit\" value = \""+p.getDenomination()+"\" readonly/>"); 
            out.println("</br>"); 
            out.println("<button type = \"submit\">Supprimer</button>");    
            out.println("</form>");
            out.println("<a href=\"http://localhost:8080/GestionStock-war/ListeProduits\"><button class = \"btn btn-primary\">Annuler</button></a>");
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
            out.println("<title>Supprimer Produit</title>");   
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
            
            String nomProduit = request.getParameter("RefProduit");
            
            boolean deleted = produitFacade.suppressionProduit(nomProduit);
            
            if(deleted){
                out.println("<div class='alert alert-success'>Le produit est supprimé avec succès</div>");
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

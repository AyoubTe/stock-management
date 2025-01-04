/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.Marque;
import Pack1.MarqueFacade;
import Pack1.ProduitFacade;
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
public class CreationProduit extends HttpServlet {

    @EJB
    private ProduitFacade produitFacade;

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
            out.println("<title>Servlet CreationProduit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreationProduit at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Creation Produit</title>");
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
            out.println("height: 70vh;");
            out.println("display: flex;");
            out.println("flex-direction: column;");
            out.println("align-items: center;");
            out.println("}");
            out.println("input,");
            out.println("select {");
            out.println("width: 80%;");
            out.println("padding: 5px;");
            out.println("height: 8%;");
            //out.println("margin: 3px;");
            out.println("border-radius: 5px;");
            out.println("}");
            out.println("button {");
            out.println("background: black;");
            out.println("color: white;");
            out.println("padding: 2px 20px;");
            out.println("border-radius: 5px;");
            out.println("}");
            out.println("</style>");
            
            out.println("<body>");
            
            out.println("<h2>Création d'un produit: </h2>");
            
            out.println("<form  method = \"POST\">");
            out.println("Reference du produit :");    
            out.println("<input type = \"text\" name = \"RefProduit\" placeholder = \"Reference du produit\" required/>"); 
            out.println("</br>"); 
            out.println("Dénomination :");
            out.println("<input type = \"text\" name = \"DenominationProduit\" placeholder = \"Dénomination du produit\" required/>"); 
            out.println("</br>"); 
            
            List<Marque> listMarques = marqueFacade.findAll();
            out.println("La marque du produit :");    
            out.println("<select name =\"NomMarque\">");
            for(Marque m : listMarques){
                out.println("<option value ="+m.getId()+">"+m.getId()+"</option>");
            }
            
            out.println("</select>");
            out.println("</br>");  // out.println("");
            out.println("Prix du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Prix\" placeholder = \"Le prix du produit en MAD\" required/>");
            out.println("</br>");
            out.println("Poids du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Poids\" placeholder = \"Poids du produit en kg\" required/>");
            out.println("</br>");
            out.println("Volume du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Volume\" placeholder = \"Volume en m3\" required/>");
            out.println("</br>");
            out.println("<button type = \"submit\">Créer le produit</button>");    
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
            out.println("<head>"); // out.println(""); request.getParameter("");
            out.println("<title>Servlet CreationProduit</title>");  
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            String refProduit = request.getParameter("RefProduit");
            String nomMarque = request.getParameter("NomMarque");
            String denomination = request.getParameter("DenominationProduit");
            float prix = Float.parseFloat(request.getParameter("Prix"));
            float poids = Float.parseFloat(request.getParameter("Poids"));
            float volume = Float.parseFloat(request.getParameter("Volume"));
            boolean created = produitFacade.creationProduit(refProduit, nomMarque, denomination, prix, poids, volume);
            if(created){
                out.println("<div class='alert alert-succes'>Le produit est crée avec succés !!</div>");
            } else {
                out.println("<div class='alert alert-danger'>Le produit existe déjà  !!</div>");
            }
            out.println("</br>");
            out.println("<a href=\"http://localhost:8080/GestionStock-war/CreationProduit\"><button class=\"btn btn-primary\">Retour</button></a>");
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

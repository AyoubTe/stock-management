/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.Marque;
import Pack1.MarqueFacade;
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

/**
 *
 * @author hp
 */
public class ModifierProduit extends HttpServlet {

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
            out.println("<title>Servlet ModifierProduit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModifierProduit at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modifier Produit</title>");  
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
            
            String refProduit = request.getParameter("produit");
            Produit p = produitFacade.find(refProduit);
            List<Marque> marques = marqueFacade.findAll();
            
            
            out.println("<form method=\"Post\">");
            out.println("Reference du produit :");    
            out.println("<input type = \"text\" name = \"RefProduit\" value = \""+p.getId()+"\" readonly/>"); 
            out.println("</br>"); 
            out.println("Dénomination :");
            out.println("<input type = \"text\" name = \"DenominationProduit\" value = \""+p.getDenomination()+"\" required/>"); 
            out.println("</br>"); 
            
            out.println("La marque du produit :");    
            out.println("<select name =\"NomMarque\">");
            out.println("<option value ="+p.getMarqueProd().getId()+">"+p.getMarqueProd().getId()+"</option>");
            for(Marque m: marques){
                String marque = m.getId();
                if(!marque.equals(p.getMarqueProd().getId())){
                    out.println("<option value ="+marque+">"+marque+"</option>");
                }
            }
            
            out.println("</select>");
            out.println("</br>");  
            out.println("Prix du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Prix\" value = \""+p.getPrix()+"\" required/>");
            out.println("</br>");
            out.println("Poids du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Poids\" value = \""+p.getPoids()+"\" required/>");
            out.println("</br>");
            out.println("Volume du produit :");
            out.println("<input  type = \"number\" step=\"0.0001\" name = \"Volume\" value = \""+p.getVolume()+"\" required/>");
            out.println("</br>");
            out.println("<button type = \"submit\">Modifier le produit</button>");
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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modifier Produit</title>");  
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
            out.println("</style>");
            
            out.println("<body>");
            String refProd = request.getParameter("RefProduit");
            String denomProd = request.getParameter("DenominationProduit");
            String marqueProd = request.getParameter("NomMarque");
            float prixPrd = Float.parseFloat(request.getParameter("Prix"));
            float poidsPrd = Float.parseFloat(request.getParameter("Poids"));
            float volumePrd = Float.parseFloat(request.getParameter("Volume"));
            boolean modified = produitFacade.modifierProduit(refProd, marqueProd, denomProd, prixPrd, poidsPrd, volumePrd);
            if(modified){
                out.println("<div class='alert alert-success'>Le produit est modifié avec succès!!</div>");
            } else{
                out.println("<div class='alert alert-danger'>Une erreur se produit lors de traitement</div>");
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

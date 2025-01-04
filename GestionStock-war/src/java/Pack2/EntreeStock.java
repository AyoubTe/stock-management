/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pack2;

import Pack1.Produit;
import Pack1.ProduitFacade;
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


public class EntreeStock extends HttpServlet {

    @EJB
    private ProduitFacade produitFacade;

    @EJB
    private StockFacade stockFacade;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EntreeStock</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EntreeStock at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Entree Stock</title>");  
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
            
            out.println("<h2>Une entrée du stock: </h2>");
            
            out.println("<form method =\"POST\">");
            out.println("Nom du stock: ");
            out.println("<select name = \"NomStock\">");
            List<Stock> stock = stockFacade.findAll();
            for(Stock s: stock){
                out.println("<option value=\""+s.getId()+"\" >"+s.getId()+"</option>");
            }
            out.println("</select>");
            
            out.println("</br>");
            out.println("Nom produit: ");
            out.println("<select name = \"RefProduit\">");
            List<Produit> produits = produitFacade.findAll();
            for(Produit p: produits){
                out.println("<option value=\""+p.getId()+"\" >"+p.getId()+"</option>");
            }     
            out.println("</select>");
            
            out.println("</br>");
            out.println("Quantité: ");
            out.println("<input type=\"number\" name=\"Quantite\" required/>");
            out.println("</br>");
            out.println("<button  type = \"submit\"> Ajouter </button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
             
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Entree Stock</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            
            String nomStock = request.getParameter("NomStock");
            String refProduit = request.getParameter("RefProduit");
            int quantite = Integer.parseInt(request.getParameter("Quantite"));
            
            boolean added = stockFacade.entreeProduitStock(nomStock, refProduit, quantite);
            if(added){
                out.println("<div class='alert alert-success'>La quantité du produit a été modifié avec succès!!</div>");
            } else{
                out.println("<div class='alert alert-danger'>Une erreur est survenue lors de la modificationd de la quantité du produit!!</div>");
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

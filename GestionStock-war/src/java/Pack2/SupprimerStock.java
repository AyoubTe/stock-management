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
public class SupprimerStock extends HttpServlet {

    @EJB
    private StockFacade stockFacade;

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
            out.println("<title>Servlet SupprimerStock</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SupprimerStock at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Supprimer Stock</title>");  
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("form { width: 50%; height: 80vh; display: flex; flex-direction: column; align-items: center; }");
            out.println("input, select { width: 80%; padding: 10px; height: 8%; margin: 5px; border-radius: 5px; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            List<Stock> stocks = stockFacade.findAll();
            
            out.println("<body>");
            //out.println("<h2>Supprimer un stock<h2>");
            out.println("<p style=\"color:red;\">Attention vous allez supprimer le stock suivant:</br>Cela va entrainer tous ses éléments de stock!!<p>");
            out.println("<form  method = \"POST\">");
            out.println("Selectionner le stock: ");
            out.println("<select name =\"NomStock\">");
            for(Stock s: stocks){
                out.println("<option value ="+s.getId()+">"+s.getId()+"</option>");
            }
            out.println("</select>");
            out.println("</br>");
            out.println("<button type=\"submit\">Supprimer</button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } catch(IOException e){
            
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
            out.println("<title>Supprimer Stock</title>");  
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
            out.println("</head>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center; width: 100%; height: 100vh; flex-direction: column; overflow: hidden; background: #f4f4f4; font-family: Arial, sans-serif; }");
            out.println("button { background: black; color: white; padding: 10px 20px; border-radius: 5px; }");
            out.println("</style>");
            
            out.println("<body>");
            
            String nomStock = request.getParameter("NomStock");
            boolean deleted = stockFacade.supprissionStock(nomStock);
            if(deleted){
                out.println("<div class='alert alert-success'>Le stock est supprimé avec succès</div>");
            } else{
                out.println("<div class='alert alert-danger'>Une erreur se produite</div>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch(IOException e){
            
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

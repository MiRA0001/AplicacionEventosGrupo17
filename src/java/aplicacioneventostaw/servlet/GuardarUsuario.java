/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.servlet;


import aplicacioneventostaw.dao.UsuarioFacade;
import aplicacioneventostaw.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mira
 */
@WebServlet(name = "GuardarUsuario", urlPatterns = {"/GuardarUsuario"})
public class GuardarUsuario extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    

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
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        Integer rol = request.getParameter("rol").isEmpty() ? 4 : new Integer(request.getParameter("rol"));
        String password = request.getParameter("password");

        Usuario usu;
        
       
        if(id!=null && !id.isEmpty()){ //Cargamos el usuario
          usu = usuarioFacade.find(new Integer(id));
        }else{ //Creamos uno nuevo
            usu = new Usuario(0);
        }
        
        usu.setEmail(email);
        usu.setRol(rol);
        usu.setPassword(password);
 
        if(id!=null && !id.isEmpty()){ //Editamos el usuario
          usuarioFacade.edit(usu);
        }else{ //Creamos uno nuevo
            usuarioFacade.create(usu);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("ListarDatosAdministradorSistema");
        rd.forward(request, response); 
       
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
        processRequest(request, response);
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
        processRequest(request, response);
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

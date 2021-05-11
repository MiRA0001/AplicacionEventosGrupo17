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
import javax.servlet.http.HttpSession;

/**
 *
 * @author migue
 */
@WebServlet(name = "Autenticar", urlPatterns = {"/Autenticar"})
public class Autenticar extends HttpServlet {
    
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
        String mail, pass, status = null, goTo="menu.jsp";
        Usuario usuario;
        RequestDispatcher rd;
        
        mail = request.getParameter("email");
        pass = request.getParameter("password");
        
        // comprobamos si el usuario está en la BD
        usuario = this.usuarioFacade.findByEmail(mail);
        
        if (usuario == null) { 
           status = "El usuario no se encuentra en la base de datos";
           request.setAttribute("status", status);
           goTo = "login.jsp";
        } else if (!pass.equals(usuario.getPassword())) { 
           status = "La clave es incorrecta";
           request.setAttribute("status", status);
           goTo = "login.jsp";                       
        } else { // el usuario está y la clave es correcta
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario); // introducimos el usuario en la sesión para saber que está autenticado
        }
        
        
        switch (usuario.getRol()) 
        {
            case 1://Administrador del sistema 
                goTo="ListarDatosAdministradorSistema";
            break;
            
            case 2://Creador de eventos  
      
            break;
            
            case 3://Analistas de eventos  
                
            break;
            
            case 4://Usuario de eventos  
                
            break;
            
            case 5://Teleoperadores  
            break;
        }
        
        rd = request.getRequestDispatcher(goTo);
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

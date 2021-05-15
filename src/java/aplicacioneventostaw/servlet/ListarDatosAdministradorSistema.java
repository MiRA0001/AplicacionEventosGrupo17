/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.servlet;

import aplicacioneventostaw.dao.EventoFacade;
import aplicacioneventostaw.dao.UsuarioFacade;
import aplicacioneventostaw.entity.Evento;
import aplicacioneventostaw.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * @author mira
 */
@WebServlet(name = "ListarDatosAdministradorSistema", urlPatterns = {"/ListarDatosAdministradorSistema"})
public class ListarDatosAdministradorSistema extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;

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
        HttpSession session = request.getSession();
        Usuario logeado = (Usuario) session.getAttribute("usuario");
        String goTo = "administradorSistema.jsp";
        if (logeado == null) {
            goTo = "Salir";
        } else {
            String email = request.getParameter("FiltroEmail");
            String rol = request.getParameter("FiltroRol");
            List<Usuario> usuarios;
            List<Evento> eventos;
            if((email==null || email.equals("")) && (rol==null || rol.equals("")) ){
            usuarios = usuarioFacade.findAll();
            }else{
                usuarios= usuarioFacade.findListByRolOrEmail(email, rol);
            }
            eventos = eventoFacade.findAll();
            request.setAttribute("eventos", eventos);
            request.setAttribute("usuarios", usuarios);
        }

        RequestDispatcher rd = request.getRequestDispatcher(goTo);
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

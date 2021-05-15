/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.servlet;

import aplicacioneventostaw.dao.EventoFacade;
import aplicacioneventostaw.entity.Evento;
import aplicacioneventostaw.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author davidyeva
 */
@WebServlet(name = "ServletEventosListar", urlPatterns = {"/ServletEventosListar"})
public class ServletEventosListar extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;

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
        String strTo = "eventos_list.jsp";
        HttpSession sesion = request.getSession();
        Usuario user = (Usuario)sesion.getAttribute("usuario");
        if (user==null){
            request.setAttribute("error", "Usuario no auntenticado");
            strTo="Salir";
        }else{
            //String filtroNombre = request.getParameter("filtroNombre");
            //String filtroSuper = request.getParameter("filtroSuper");
            //String [] filtroSuper = request.getParameterValues("filtroSuper");
            
            Boolean filtradoTag = false;
            Boolean filtrado = false;
            String filtroTitulo="";
                    
            String tag = request.getParameter("tag");
            if (request.getParameter("filtrotitulo")!=null){
                filtroTitulo = new String(request.getParameter("filtrotitulo").getBytes("ISO-8859-1"),"UTF-8").toUpperCase();
            }
            String soloTitulo=request.getParameter("solotitulo");
            String fechaInicio = request.getParameter("fechainicio");
            String fechaFin = request.getParameter("fechafin");
            String caducados = request.getParameter("caducados");
            
            List<Evento> lista=new ArrayList();
            
            if (tag==null || tag==""){
                if ((filtroTitulo!=null && filtroTitulo.length()>0)){
                    // Búsqueda por cadena
                    filtrado=true;
                    if ((soloTitulo!=null && soloTitulo.length()>0)){
                        if ((fechaInicio!=null && fechaInicio.length()>0)&&(fechaFin!=null && fechaFin.length()>0)){
                            if ((caducados!=null && caducados.length()>0)){
                                // Búsqueda por cadena (sólo título), fecha y caducados 1111
                                lista = this.eventoFacade.getEventosByUserAndTodoTitulo(user.getUsuarioId(),filtroTitulo, fechaInicio, fechaFin);
                            }else{
                                // Búsqueda por cadena (sólo título) y fecha 1110
                                lista = this.eventoFacade.getEventosByUserAndTituloAndFecha(user.getUsuarioId(),filtroTitulo, fechaInicio, fechaFin);
                            }
                        }else{
                            if ((caducados!=null && caducados.length()>0)){
                                    // Búsqueda por cadena (sólo título) y caducados 1101
                                    lista = this.eventoFacade.getEventosByUserAndTituloAndCaducados(user.getUsuarioId(), filtroTitulo);
                            }else{
                                    // Búsqueda por cadena (sólo título) 1100
                                    lista = this.eventoFacade.getEventosByUserAndTitulo(user.getUsuarioId(),filtroTitulo);
                            }
                        }	
                    }else{	
                        if ((fechaInicio!=null && fechaInicio.length()>0)&&(fechaFin!=null && fechaFin.length()>0)){
                            if ((caducados!=null && caducados.length()>0)){
                                    // Búsqueda por cadena, fecha y caducados 1011
                                    lista = this.eventoFacade.getEventosByUserAndTodoFiltro(user.getUsuarioId(),filtroTitulo, fechaInicio, fechaFin);
                            }else{
                                    // Búsqueda por cadena y fecha 1010
                                    lista = this.eventoFacade.getEventosByUserAndFiltroAndFecha(user.getUsuarioId(),filtroTitulo, fechaInicio, fechaFin);
                            }
                        }else{
                            if ((caducados!=null && caducados.length()>0)){
                                    // Búsqueda por cadena y caducados 1001
                                    lista = this.eventoFacade.getEventosByUserAndFiltroAndCaducados(user.getUsuarioId(), filtroTitulo);
                            }else{
                                    // Búsqueda por cadena 1000
                                    lista = this.eventoFacade.getEventosByUserAndCadena(user.getUsuarioId(), filtroTitulo);
                            }
                        }	
                    }
                }else{
                    if ((fechaInicio!=null && fechaInicio.length()>0)&&(fechaFin!=null && fechaFin.length()>0)){
                        filtrado=true;
                        if ((caducados!=null && caducados.length()>0)){
                                // Búsqueda por fecha y caducados 0011
                                lista = this.eventoFacade.getEventosByUserAndFechaAndCaducados(user.getUsuarioId(), fechaInicio, fechaFin);
                        }else{
                                // Búsqueda por fecha 0010
                                lista = this.eventoFacade.getEventosByUserAndFecha(user.getUsuarioId(), fechaInicio, fechaFin);
                        }
                    }else{
                        if ((caducados!=null && caducados.length()>0)){
                            filtrado=true;
                            // Búsqueda por caducados 0001
                            lista = this.eventoFacade.getEventosByUserAndCaducados(user.getUsuarioId());
                        }else{
                            // Sin filtrado 0000
                            filtrado=false;
                            lista = this.eventoFacade.getEventosByUser(user.getUsuarioId());
                        }
                    }	
                }
            }else{
                filtradoTag=true;
                filtrado=false;
                lista = this.eventoFacade.getEventosByUserAndTag(user.getUsuarioId(),"#"+tag);
            }
            
          
            request.setAttribute("lista", lista);
            request.setAttribute("filtrado", filtrado);
            request.setAttribute("filtradotag", filtradoTag);
            request.setAttribute("tag", tag);
            request.setAttribute("filtrotitulo", filtroTitulo);
            request.setAttribute("solotitulo", soloTitulo);
            request.setAttribute("fechainicio", fechaInicio);
            request.setAttribute("fechafin", fechaFin);
            request.setAttribute("caducados", caducados);
            
        }

        RequestDispatcher rd = request.getRequestDispatcher(strTo);
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

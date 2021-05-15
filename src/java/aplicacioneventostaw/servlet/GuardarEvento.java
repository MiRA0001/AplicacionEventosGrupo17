/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.servlet;

import aplicacioneventostaw.dao.EtiquetaFacade;
import aplicacioneventostaw.dao.EventoFacade;
import aplicacioneventostaw.entity.Etiqueta;
import aplicacioneventostaw.entity.Evento;
import aplicacioneventostaw.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@WebServlet(name = "GuardarEvento", urlPatterns = {"/GuardarEvento"})
public class GuardarEvento extends HttpServlet {

    @EJB
    private EtiquetaFacade etiquetaFacade;

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
            throws ServletException, IOException, ParseException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
         String goTo = "";
        if (usuario == null) {
            goTo = "Salir";
        } else {
            
            String id, titulo, descripcion, fecha, hora, aforo, precio, fechafin, maxentradas, numeradas, filas, asientosfila, etiquetasStr;
            Evento evento;
            
            List<Etiqueta> etiquetas = new ArrayList();
            
            etiquetasStr = new String(request.getParameter("etiquetas").getBytes("ISO-8859-1"),"UTF-8");
            
            Etiqueta aux;
            
            Scanner sc = new Scanner(etiquetasStr);
            sc.useDelimiter("[ \n]+");
            while (sc.hasNext()){
		String st = sc.next();
                if (st.startsWith("#")){
                    aux = this.etiquetaFacade.getEtiquetaByNombre(st);
                    if (aux==null){
                        etiquetas.add(new Etiqueta(0,st));
                    }else{
                        etiquetas.add(aux);
                    }
                }
            }
            
            id = request.getParameter("id");
            titulo = new String(request.getParameter("titulo").getBytes("ISO-8859-1"),"UTF-8");
            descripcion = new String(request.getParameter("descripcion").getBytes("ISO-8859-1"),"UTF-8");
            fecha = request.getParameter("fecha");
            hora = request.getParameter("hora");
            aforo = request.getParameter("aforo");
            precio = request.getParameter("precio");
            fechafin = request.getParameter("fechafin");
            maxentradas = request.getParameter("maxentradas");
            if (("Si").equals(request.getParameter("numeradas"))){
                numeradas = "true";
            }else{
                numeradas = "false";
            }
            filas = request.getParameter("filas");
            asientosfila = request.getParameter("asientosfila");
 
            if (id==null || id.isEmpty()){
                evento = new Evento();
            }else{
                evento = this.eventoFacade.find(new Integer(id));
            }
            evento.setTitulo(titulo);
            evento.setDescripcion(descripcion);
            evento.setFechaEvento(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
            evento.setHora(new SimpleDateFormat("HH:mm").parse(hora));
            evento.setAforo(new Integer(aforo));
            evento.setPrecio(new BigDecimal(precio));
            evento.setFechaMaximaReserva(new SimpleDateFormat("yyyy-MM-dd").parse(fechafin));
            evento.setMaximoEntradasPorUsuario(new Integer(maxentradas));
            evento.setEntradasNumeradas(new Boolean(numeradas));
            evento.setFilas(new Integer(filas));
            evento.setAsientosPorFila(new Integer(asientosfila));
            if (id==null || id.isEmpty()){
                evento.setCreadorId(usuario);
                this.eventoFacade.create(evento);
                evento.setEtiquetaList(etiquetas);
                this.eventoFacade.edit(evento);
                evento.setEtiquetaList(etiquetas);
            }else{
                evento.setEtiquetaList(new ArrayList());
                this.eventoFacade.edit(evento);
                evento.setEtiquetaList(etiquetas);
                this.eventoFacade.edit(evento);
            }
            
            if(usuario.getRol()==1){
               goTo = "ListarDatosAdministradorSistema";
            }else if (usuario.getRol()==2){
                goTo = "ServletEventosListar";
            }
        }
        
        response.sendRedirect(goTo);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GuardarEvento.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GuardarEvento.class.getName()).log(Level.SEVERE, null, ex);
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

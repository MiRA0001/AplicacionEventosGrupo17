<%-- 
    Document   : eventoCRU
    Created on : 13-may-2021, 19:59:22
    Author     : mira
--%>

<%@page import="java.util.Date"%>
<%@page import="aplicacioneventostaw.entity.Evento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aplicacioneventostaw.entity.Etiqueta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Evento</title>

        <%

            Evento evento = (Evento) request.getAttribute("evento");
            String eventoId = "", eventoTitulo = "", eventoDescripcion = "", eventoPrecio = "",
                    eventoFecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                    eventoHora = new SimpleDateFormat("HH:mm").format(new Date()),
                    eventoFechaReserva = new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                    eventoAforo = "", eventoEntradasUsuario = "", eventoFilas = "",
                    eventoAsientosFila = "", eventoEtiquetas = "";
            
            Boolean eventoNumeradas = false;

            if (evento != null) {
                eventoId = evento.getEventoId().toString();
                eventoTitulo = evento.getTitulo();
                eventoDescripcion = evento.getDescripcion();
                eventoPrecio = evento.getPrecio().toString();
                eventoFecha = new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaEvento());
                eventoHora = new SimpleDateFormat("HH:mm").format(evento.getHora());
                eventoAforo = new Integer(evento.getAforo()).toString();
                eventoFechaReserva = new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaMaximaReserva());
                eventoEntradasUsuario = new Integer(evento.getMaximoEntradasPorUsuario()).toString();
                eventoNumeradas = evento.getEntradasNumeradas();
                eventoFilas = evento.getFilas().toString();
                eventoAsientosFila = evento.getAsientosPorFila().toString();
                for (Etiqueta e : evento.getEtiquetaList()) {
                    eventoEtiquetas = eventoEtiquetas + e.getNombre() + " ";
                }
            }
        %>

    </head>
    <body>
        <h1>Datos del Evento</h1>
        
        <form action="GuardarEvento">
            <input type="hidden" name="id" value="<%= eventoId %>" /><br/>
            <b>TÍTULO: </b>
            </td></tr><tr><td>
            <textarea name="titulo" rows="4" cols="125"><%= eventoTitulo %></textarea><br/><br/>
            </td></tr>
            <tr><td>
            <b>DESCRIPCIÓN:</B><br/>
            </td></tr><tr><td>
            <textarea name="descripcion" rows="10" cols="125"><%= eventoDescripcion%></textarea><br/>
            </td></tr>
            <tr><td align="left">
            <b>FECHA: </b> <input type="text" name="fecha" size="8" value="<%= eventoFecha %>" />
            <b>HORA: </b> <input type="text" name="hora" size="5" value="<%= eventoHora %>" />
            <b>AFORO: </b> <input type="text" name="aforo" size="5" value="<%= eventoAforo %>" />
            <b>PRECIO: </b> <input type="text" name="precio" size="5" value="<%= eventoPrecio %>" />
            <b>MAXIMO DE ENTRADAS/USUARIO: </b> <input type="text" name="maxentradas" size="5" value="<%= eventoEntradasUsuario %>" /><br/>
            <b>FECHA FIN RESERVA: </b> <input type="text" name="fechafin" size="8" value="<%= eventoFechaReserva %>" />
            <b>ENTRADAS NUMERADAS: </b><select name="numeradas">
            <%
            if (eventoNumeradas){
            %>    
                <option>Si</option>
                <option>No</option>
            <%
            }else{
            %>
                <option>No</option>
                <option>Si</option>
            <%
            }
            %>
            </select>
            <b>FILAS: </b></b> <input type="text" name="filas" size="5" value="<%= eventoFilas %>" />
            <b>ASIENTOS POR FILA: </b></b> <input type="text" name="asientosfila" size="5" value="<%= eventoAsientosFila %>" /><br/>
            </td></tr>
            <tr><td>
            <b>TAGS:</b><br/>
            <textarea name="etiquetas" rows="4" cols="125"><%= eventoEtiquetas%></textarea><br/></td></tr><tr>
            <br/><td><input type="submit" value="Enviar" />
            
            </form>
            
            <button><a href="ListarDatosAdministradorSistema">Cancelar</a></button>
    </body>
</html>

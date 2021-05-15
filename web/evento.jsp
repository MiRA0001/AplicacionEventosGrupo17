<%-- 
    Document   : eventos
    Created on : 11-may-2021, 7:03:07
    Author     : davidyeva
--%>


<%@page import="aplicacioneventostaw.entity.Evento"%>
<%@page import="aplicacioneventostaw.entity.Etiqueta"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta/Edición del evento</title>
    </head>
    <%
        String status = (String)request.getAttribute("status");
        Evento evento = (Evento)request.getAttribute("evento");
        String eventoId="", eventoTitulo="", eventoDescripcion="", eventoPrecio="0.00",
                eventoFecha=new SimpleDateFormat("yyyy-MM-dd").format(new Date()), eventoHora=new SimpleDateFormat("HH:mm").format(new Date()),
                eventoFechaReserva=new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                eventoAforo="0", eventoEntradasUsuario="0", eventoFilas="0", eventoAsientosFila="0", eventoEtiquetas="";
        Boolean eventoNumeradas=false;
        
        if (evento!=null){
            eventoId=evento.getEventoId().toString();
            eventoTitulo=evento.getTitulo();
            eventoDescripcion=evento.getDescripcion();
            eventoPrecio=evento.getPrecio().toString();
            eventoFecha=new SimpleDateFormat("yyyy-MM-dd").format(evento.getFechaEvento());
            eventoHora=new SimpleDateFormat("HH:mm").format(evento.getHora());
            eventoAforo=new Integer(evento.getAforo()).toString();
            eventoFechaReserva=new SimpleDateFormat("yyyy-MM-dd").format(evento.getFechaMaximaReserva());
            eventoEntradasUsuario=new Integer(evento.getMaximoEntradasPorUsuario()).toString();
            eventoNumeradas=evento.getEntradasNumeradas();
            eventoFilas=evento.getFilas().toString();
            eventoAsientosFila=evento.getAsientosPorFila().toString();
            for (Etiqueta e:evento.getEtiquetaList()){
                eventoEtiquetas = eventoEtiquetas + e.getNombre()+" ";
            }
        }
    %>
    <body>
        <table align="center" width="55%"><tr><td>
        <h1>
        <%
        if (status=="Editando"){
        %>
            Editando 
        <%
        }else{
        %>
            Creando 
        <%
        }
        %>
        evento</h1></td></tr><tr><td align="left">
        <form action="GuardarEvento">
            <input type="hidden" name="id" value="<%= eventoId %>" /><br/>
            <b>TÍTULO: </b>
            </td></tr><tr><td>
            <textarea name="titulo" rows="4" cols="125" required><%= eventoTitulo %></textarea><br/><br/>
            </td></tr>
            <tr><td>
            <b>DESCRIPCIÓN:</B><br/>
            </td></tr><tr><td>
            <textarea name="descripcion" rows="10" cols="125"><%= eventoDescripcion%></textarea><br/>
            </td></tr>
            <tr><td align="left">
            <b>FECHA: </b> <input type="date" name="fecha" size="8" value="<%= eventoFecha %>" required />
            <b>HORA: </b> <input type="time" name="hora" size="5" value="<%= eventoHora %>" required />
            <b>AFORO: </b> <input type="number" name="aforo" size="8" min="0" value="<%= eventoAforo %>" required style="text-align:right;"/>
            <b>PRECIO: </b> <input type="number" name="precio" step="0.01" size="10" min="0" value="<%= eventoPrecio %>" required style="text-align:right;"/>
            <b>MAXIMO DE ENTRADAS/USUARIO: </b> <input type="number" name="maxentradas" size="5" min="0" value="<%= eventoEntradasUsuario %>" required style="text-align:right;"/><br/>
            <b>FECHA FIN RESERVA: </b> <input type="date" name="fechafin" size="8" min="0" value="<%= eventoFechaReserva %>" required />
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
            <b>FILAS: </b></b> <input type="number" name="filas" size="5" min="0" value="<%= eventoFilas %>" required style="text-align:right;"/>
            <b>ASIENTOS POR FILA: </b></b> <input type="number" name="asientosfila" size="5" min="0" value="<%= eventoAsientosFila %>" required style="text-align:right;"/><br/>
            </td></tr>
            <tr><td>
            <b>TAGS:</b><br/>
            <textarea name="etiquetas" rows="4" cols="125"><%= eventoEtiquetas%></textarea><br/></td></tr><tr>
            <br/><td><input type="submit" value="Enviar" />
            
            </form><a href="ServletEventosListar"><input type="submit" value="Cancelar" /></a></td></tr></table>
    </body>
</html>
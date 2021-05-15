<%-- 
    Document   : eventos_list.jsp
    Created on : 10-may-2021, 17:49:32
    Author     : davidyeva
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="aplicacioneventostaw.entity.Etiqueta"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aplicacioneventostaw.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="aplicacioneventostaw.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de eventos</title>
    </head>
    <%
        DecimalFormat formato = new DecimalFormat("#0.00");
        
        Boolean filtrado = (Boolean)request.getAttribute("filtrado");
        Boolean filtradoTag = (Boolean)request.getAttribute("filtradotag");
        
        String tag = (String)request.getAttribute("tag");
        String filtroTitulo = (String)request.getAttribute("filtrotitulo");
        String soloTitulo = (String)request.getAttribute("solotitulo");
        String fechaInicio = (String)request.getAttribute("fechainicio");
        String fechaFin = (String)request.getAttribute("fechafin");
        String caducados = (String)request.getAttribute("caducados");
        
        List<Evento> lista = (List)request.getAttribute("lista");

        HttpSession sesion = request.getSession();
        Usuario user = (Usuario)sesion.getAttribute("usuario");
        
        if (!lista.isEmpty()){
        

    %>
    <body>
            <table width="55%" align="center"><tr><td>
                <h1>Lista de eventos de <%= user.getEmail() %>
                <%
                if(filtrado || filtradoTag){
                %>
                    <label style="color: blue">( filtrada
                    <%if (filtradoTag){%>
                        por el tag #<%= tag %> )
                <%}else{%>
                        )</label>
                <%}%>
            <a href="ServletEventosListar"><input type="submit" value="Quitar Filtro" /></a>
            <%
                }
            %>
            </h1><br/>
            </td></tr><tr><td>
            <a href="CrearEvento"><input type="submit" value="Crear nuevo evento" /></a><br/><br/>
            <%if (!filtradoTag){%>
            <form action="ServletEventosListar">
            <input type="hidden" name="tag" <% if (tag!=null) {%> value="<%= tag%>" <%}%> >
            Búsqueda: <input type="text" name="filtrotitulo" size="20" <% if (filtroTitulo!=null){%> value="<%= filtroTitulo.toLowerCase() %>"><%}%> 
            Sólo en título <input type="checkbox" name="solotitulo" value="true" <% if (soloTitulo!=null){%> checked = "checked"<%}%>/>&nbsp
            Fecha entre <input type="date" name="fechainicio" <% if (fechaInicio!=null){%> value="<%= fechaInicio %>"<%}%> /> y
            <input type="date" name="fechafin" <% if (fechaFin!=null){%> value="<%= fechaFin %>"<%}%> />&nbsp
            Eventos caducados <input type="checkbox" name="caducados" value="true" <% if (caducados!=null){%> checked = "checked"<%}%>/>&nbsp&nbsp&nbsp
            <input type="submit" value="Filtrar" />
            </form>
            <%}%>
            </td>
            </tr></table>
            <table border="5" width="55%" align="center">
            <%
                for (Evento e:lista){
            %>
            <tr>
                <td>
                <%
                if (e.getFechaEvento().compareTo(new Date())<0){
                %>                       
                <center><b><label style="color: rgb(205, 92, 92);">EVENTO CADUCADO</b></center>
                <label style="color: graytext")>
                <%
                }        
                %>
                <b>ID</b>: <%= e.getEventoId() %><br/><b>TITULO: </b><%= e.getTitulo() %><br/>
                <b>DESCRIPCION: </b><br/><%= e.getDescripcion() %><br/>
                <b>FECHA: </b><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaEvento()) %> 
                <b>HORA: </b><%= new SimpleDateFormat("HH:mm").format(e.getHora()) %>  
                <b>ENTRADAS DISPONIBLES HASTA: </b><%= new SimpleDateFormat("dd/MM/yyyy").format(e.getFechaMaximaReserva()) %><br/>
                <b>PRECIO: </b>
                <%
                String temp = e.getPrecio().toString();
                if (e.getPrecio().toString().equals("0.00")){    
                %>
                    Gratis - 
                <%
                }else{
                %>
                    <%= formato.format(e.getPrecio()) %> € -
                <%
                }
                %>
                Entradas
                <%
                if (!e.getEntradasNumeradas()){
                %>
                no
                <%
                }
                %>
                numeradas (hasta
                <%
                if (e.getMaximoEntradasPorUsuario()>1){
                %>
                <%= e.getMaximoEntradasPorUsuario() %> entradas
                <%
                }else{
                %>
                <%= e.getMaximoEntradasPorUsuario() %> entrada
                <%
                }
                %>
                por usuario)<br/>
                <b>ETIQUETAS:</b><br/>
                <%
                if (e.getEtiquetaList().isEmpty()){
                %>
                    No hay etiquetas
                <%
                }else{
                    for (Etiqueta etiqueta:e.getEtiquetaList()){
                        String param = etiqueta.getNombre().replace("#", "");
                %>
                <a href="ServletEventosListar?tag=<%= param %>"><%= etiqueta.getNombre() %></a>&nbsp 
                <%
                    }
                }
                %>
                                <%
                if (e.getFechaEvento().before(new Date())){
                %>                       
                </label>
                <%
                }        
                %>
                </td>
                <td align="center"><a href="EditarEvento?id=<%= e.getEventoId() %>"><input type="submit" value="Editar"/></a><br/><br/>
                <a href="ServletEventoBorrar?id=<%= e.getEventoId() %>"><input type="submit" value="Borrar" /></a></td>
            </tr>
            <%
                }
            %>
            </table>
        <%
        }else{
        %>
            <table width="55%" align="center"><tr><td>
                <h1>Lista de eventos de <%= user.getEmail() %>
                <%
                if(filtrado || filtradoTag){
                %>
                    <label style="color: blue">( filtrada
                    <%if (filtradoTag){%>
                        por el tag #<%= tag %> )
                <%}else{%>
                        )</label>
                <%}%>
            <a href="ServletEventosListar"><input type="submit" value="Quitar Filtro" /></a>
            <%
                }
            %>
            </h1><br/>
            </td></tr><tr><td>
            <a href="CrearEvento"><input type="submit" value="Crear nuevo evento" /></a><br/><br/>
            <form action="ServletEventosListar">
            <input type="hidden" name="tag" <% if (tag!=null) {%> value="<%= tag%>" <%}%> >
            Búsqueda: <input type="text" name="filtrotitulo" size="40" <% if (filtroTitulo!=null){%> value="<%= filtroTitulo.toLowerCase() %>"><%}%> 
            Sólo en título <input type="checkbox" name="solotitulo" value="true" <% if (soloTitulo!=null){%> checked = "checked"<%}%>/>&nbsp
            Fecha entre <input type="date" name="fechainicio" <% if (fechaInicio!=null){%> value="<%= fechaInicio %>"<%}%> /> y
            <input type="date" name="fechafin" <% if (fechaFin!=null){%> value="<%= fechaFin %>"<%}%> />&nbsp
            Eventos caducados <input type="checkbox" name="caducados" value="true" <% if (caducados!=null){%> checked = "checked"<%}%>/>&nbsp&nbsp&nbsp
            <input type="submit" value="Filtrar" />
            </form>
            </td>
                <tr><td></h1>
                        <h2>No hay eventos</h2>
            <a href="CrearEvento"><input type="submit" value="Crear nuevo evento" /></a><br/><br/>
            </td></tr>
        <%}%>
        <table width="55%" align="center"><tr><td>    
        <a href="Salir"><input type="submit" value="Cerrar sesión" /></a>
        </td></tr></table>
    </body>
</html>

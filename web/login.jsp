<%-- 
    Document   : login
    Created on : 29-abr-2021, 11:00:28
    Author     : migue
--%>


<%@page import="aplicacioneventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        Usuario usuario;
        String status;
        
        usuario = (Usuario)session.getAttribute("usuario");
        if (usuario != null) {
            response.sendRedirect("menu.jsp");  
            return;
        } 
                
        // Si se llama a esta JSP desde el servlet "Autenticar", se le enviará
        // el atributo "status"con el mensaje de error. 
        status = (String)request.getAttribute("status");
        if (status == null) {
            status = "";
        }                
    %>
        <h1>Bienvenido a la App de Eventos</h1>
        <form method="post" action="Autenticar">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"/></td>                
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>                                    
                </tr>            
                <tr>
                    <td colspan="2"><input type="submit" value="Enviar"/></td>
                </tr>
                <tr>                
                    <td colspan="2"><%= status %></td>                    
                </tr>
            </table>
        </form>            
    </body>
</html>

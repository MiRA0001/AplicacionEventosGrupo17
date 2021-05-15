<%-- 
    Document   : usuarioCU
    Created on : 07-may-2021, 18:53:45
    Author     : mira
--%>


<%@page import="aplicacioneventostaw.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion del Usuario</title>
        <%
            Usuario admin = (Usuario) request.getSession().getAttribute("usuario");
            String log = admin.getEmail();
            Usuario usu = (Usuario) request.getAttribute("usuario");
            String email = "", password="",  id = "", rol = "";
            if (usu != null) {
                id = usu.getUsuarioId().toString();
                email = usu.getEmail();
                rol = usu.getRol().toString();
                password =usu.getPassword();
            }
        %>
    </head>
    <body>
        <h1>Estas logeado como ( <%=log%> )</h1>
        <h2>Panel de Datos de Usuario</h2>

        <form method="POST" action="GuardarUsuario"  >
            <input type="hidden" name="id" value="<%=id%>"><br>
            <label for="email" >Email</label><br>
            <input type="email" name="email" value="<%=email%>" maxlength = "50" required ><br
            <label for="password" >Contraseña</label><br>
            <input type="password" name="password" value="<%=password%>" maxlength = "50" required ><br>
            <label for="rol">Rol</label><br>
            <input type="number"  name="rol" value="<%=rol%>"  min="1" max="4" required ><br><br><br>
            <label for="nombre" >Nombre</label><br>
            <input type="submit" value="Guardar">
        </form>
        <button><a href="ListarDatosAdministradorSistema">Cancelar</a></button>
    </body>
</html>

<%-- 
    Document   : administradorSistema
    Created on : 07-may-2021, 9:45:57
    Author     : mira
--%>



<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aplicacioneventostaw.entity.Evento"%>
<%@page import="aplicacioneventostaw.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>

            .fixTableHead {
                overflow-y: auto;
                height: 200px;
            }
            .fixTableHead thead th {
                position: sticky;
                top: 0;
            }
            table {
                border-collapse: collapse;        
                width: 100%;
            }
            th,
            td {
                padding: 8px 15px;
                border: 2px solid #529432;
            }
            th {
                background: #ABDD93;
                text-align: left;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion del Sistema</title>

        <%
            Usuario usuario;
            List<Usuario> lista_usuarios;
            List<Evento> lista_eventos;
            usuario = (Usuario) session.getAttribute("usuario");
            lista_usuarios = (List<Usuario>) request.getAttribute("usuarios");
            lista_eventos = (List<Evento>) request.getAttribute("eventos");

        %>
    </head>
    <body>
        <h1>Bienvenido usuario administrador ( <%= usuario.getEmail()%> )</h1>

        <h2>Datos de Usuarios</h2>

        <form action="ListarDatosAdministradorSistema" method="get">
            <label for="email" >Email: </label>
            <input type="text" name="FiltroEmail" maxlength = "50" >
            <label for="rol" >Rol: </label>
            <input type="number"  name="FiltroRol" min="1" max="4"  >
            <input type="submit" value="Filtrar">
        </form> 

        <br>

        <div class="fixTableHead">
            <table >
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Rol</th>
                        <th>Borrar Usuario</th>
                        <th>Editar Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <%  for (Usuario usu : lista_usuarios) {
                    %>
                    <tr>
                        <td><%= usu.getUsuarioId()%></td>
                        <td><%= usu.getEmail()%></td>
                        <td><%= usu.getRol()%></td>
                        <td>
                            <a href="BorrarUsuario?id=<%= usu.getUsuarioId()%>">Borrar</a>
                        </td>
                        <td>
                            <a href="EditarUsuario?id=<%=usu.getUsuarioId()%>" >Editar</a>
                        </td>
                    </tr>

                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <br>
        <button> <a href="CrearUsuario"> Crear Usuario</a> </button> 
        <br><br>


        <h2>Datos de Eventos</h2>
        <div class="fixTableHead">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titulo</th>
                        <th>Descripcion</th>
                        <th>Aforo</th>
                        <th>Id Usuario</th>
                        <th>Fecha del Evento</th>
                        <th>Fecha maxima reserva</th>
                        <th>Precio</th>
                        <th>Borrar Evento</th>
                        <th>Ver/Editar Evento</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        for (Evento evento : lista_eventos) {
                            String descripcion = evento.getDescripcion() == null ? "" : evento.getDescripcion();
                    %>
                    <tr>
                        <td><%= evento.getEventoId()%></td>
                        <td><%= evento.getTitulo()%></td>
                        <td><%= descripcion%></td>
                        <td><%= evento.getAforo()%></td>
                        <td><%= evento.getCreadorId().getUsuarioId()%></td>
                        <td><%= sdf.format(evento.getFechaEvento())%></td>
                        <td><%= sdf.format(evento.getFechaMaximaReserva())%></td>
                        <td><%= evento.getPrecio()%></td>
                        <td>
                            <a href="ServletEventoBorrar?id=<%=evento.getEventoId()%>">Borrar</a>
                        </td>
                        <td>
                            <a href="EditarEvento?id=<%=evento.getEventoId()%>" >Ver/Editar</a>
                        </td>
                    </tr>

                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <button> <a href="CrearEvento"> Crear Evento</a> </button>
        <br><br>
        <a href="Salir">Salir del sistema</a> <br/> 
    </body>

</html>

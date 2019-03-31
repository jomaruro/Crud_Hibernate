<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    List<Cliente> lstClientes = (List<Cliente>) session.getAttribute("clientes");
    Usuario usuario = new Usuario();
    if (session.getAttribute("logueado") != null) {
        usuario = ((Usuario) session.getAttribute("logueado"));
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.css" media="screen" />
        <link rel="stylesheet" href="css/estilos.css" type="text/css" media="screen" />

        <script src="plugins/jQuery/jquery-2.1.4.min.js"></script>
        <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="js/fnClientes.js"></script>

        <title>TPV Hibernate</title>
    </head>
    <body class="admin-body">
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <div class="form-group">
                <form id="formClientes" action="SCliente" method="post">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Cif</th>
                                <th scope="col">Dirección</th>
                                <th scope="col">Localidad</th>
                                <th scope="col">Provincia</th>
                                <th scope="col" colspan="2">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row"><input class="form-control" type="text" name="nombre" size="30" required /></th>
                                <td><input class="form-control" type="text" name="cif" size="14" /></td>
                                <td><input class="form-control" type="text" name="direccion" size="30" /></td>
                                <td><input class="form-control" type="text" name="localidad" size="20" /></td>
                                <td><input class="form-control" type="text" name="provincia" size="20" /></td>
                                <td class="acciones">
                                    <button type="submit" class="btn btn-primary glyphicon glyphicon-floppy-disk" name="accion" value="insertar"></button>
                                </td>
                                <td class="acciones">
                                    <button type="reset" class="btn btn-danger glyphicon glyphicon-remove" title="Cancelar"></button>
                                </td>
                            </tr>
                            <% if (lstClientes.size() > 0) {
                                    Iterator<Cliente> itr = lstClientes.iterator();
                                    while (itr.hasNext()) {
                                                    Cliente cliente = (Cliente) itr.next();%>
                            <tr id="linea<%=cliente.getIdCliente()%>">
                                <th scope="row">
                                    <input class="form-control" type="text" id="nombre<%=cliente.getIdCliente()%>" name="nombre<%=cliente.getIdCliente()%>" value="<%=cliente.getNombre()%>" />
                                </th>
                                <td>
                                    <input class="form-control" type="text" id="cif<%=cliente.getIdCliente()%>" name="cif<%=cliente.getIdCliente()%>" value="<%=cliente.getCif()%>" />
                                </td>
                                <td>
                                    <input class="form-control" type="text" id="direccion<%=cliente.getIdCliente()%>" name="direccion<%=cliente.getIdCliente()%>" value="<%=cliente.getDireccion()%>" />
                                </td>
                                <td>
                                    <input class="form-control" type="text" id="localidad<%=cliente.getIdCliente()%>" name="localidad<%=cliente.getIdCliente()%>" value="<%=cliente.getLocalidad()%>" />
                                </td>
                                <td>
                                    <input class="form-control" type="text" id="provincia<%=cliente.getIdCliente()%>" name="provincia<%=cliente.getIdCliente()%>" value="<%=cliente.getProvincia()%>"/>
                                </td>
                                <td class="acciones">
                                    <a href="#" data-id="<%=cliente.getIdCliente()%>" class="btn btn-primary btnEditar">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </a>
                                </td>
                                <td class="acciones">
                                    <a href="#" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </a>
                                </td>
                            </tr>
                            <%} %>
                            <%} else { %>
                            <tr>
                                <td colspan="4">
                                    <p>No hay Clientes que mostrar</p>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>

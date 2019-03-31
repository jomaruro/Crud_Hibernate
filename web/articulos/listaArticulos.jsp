<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    List<Articulo> lstArticulos = (List<Articulo>) session.getAttribute("articulos");
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
        <script src="js/fnArticulos.js"></script>
        <title>TPV Hibernate</title>
    </head>
    <body class="admin-body">
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <div class="form-group">
                <form id="formArticulos" action="SArticulo" method="post">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col" colspan="2">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row"><input class="form-control" type="text" name="nombre" size="100" required /></th>
                                <td><input class="form-control numeros" type="number" name="precio" size="10" step=".01" required/></td>
                                <td class="acciones">
                                    <button type="submit" class="btn btn-primary glyphicon glyphicon-floppy-disk" name="accion" value="insertar" title="Guardar Artículo"></button>
                                </td>
                                <td class="acciones">
                                    <button type="reset" class="btn btn-danger glyphicon glyphicon-remove" title="Cancelar"></button>
                                </td>
                            </tr>
                            <% if (lstArticulos.size() > 0) {
                                    Iterator<Articulo> itr = lstArticulos.iterator();
                                    while (itr.hasNext()) {
                                                    Articulo articulo = (Articulo) itr.next();%>
                            <tr id="linea<%=articulo.getId()%>">
                                <th scope="row">
                                    <input class="form-control" size="100" type="text" id="nombre<%=articulo.getId()%>" name="nombre<%=articulo.getId()%>" value="<%=articulo.getNombre()%>" /> 
                                </th>
                                <td class="numeros">
                                    <input class="form-control numeros" size="10" step=".01" type="number" id="precio<%=articulo.getId()%>" name="precio<%=articulo.getId()%>" value="<%=articulo.getPrecio()%>" />
                                </td>
                                <td class="acciones">
                                    <a href="#" data-id="<%=articulo.getId()%>" class="btn btn-primary btnEditar"  title="Editar Artículo">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </a>
                                </td>
                                <td class="acciones">
                                    <a href="#" class="btn btn-danger" title="Eliminar Artículo">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </a>
                                </td>
                            </tr>
                            <%} %>
                            <%} else { %>
                            <tr>
                                <td colspan="4">
                                    <p>No hay Artículos que mostrar</p>
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
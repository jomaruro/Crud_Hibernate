<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    List<Cabecera> lstCabecera = (List<Cabecera>) session.getAttribute("cabeceras");

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
        <script src="js/fnFacturas.js"></script>
        <title>TPV Hibernate - Lista Facturas</title>
    </head>
    <body>
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <a href="SCabecera?accion=nueva" class="btn btn-info" title="Nueva Factura">
                <span class="glyphicon glyphicon-plus-sign" title="Nueva Factura"></span>
            </a>
            <div class="form-group">
                <% if (lstCabecera.size() > 0) {%>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Nº</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Importe</th>
                            <th scope="col">Usuario</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%Iterator<Cabecera> itr = lstCabecera.iterator();
                            while (itr.hasNext()) {
                                Cabecera cabecera = (Cabecera) itr.next();
                                double importe = 0;
                                Iterator<Detalle> itrDetalles = cabecera.getDetalles().iterator();
                                while (itrDetalles.hasNext()) {
                                    Detalle detalle = (Detalle) itrDetalles.next();
                                    importe += detalle.getCantidad() * detalle.getPrecio();
                                }
                        %>
                        <tr>
                            <th scope="row"><%=cabecera.getId().getNumero()%></th>
                            <td>
                                <%=cabecera.getCliente().getNombre()%>
                            </td>
                            <td class="numeros">
                                <%=importe %>
                            </td>
                            <td>
                                <%=cabecera.getUsuario().getNombre()%>
                            </td>
                            <td>
                                <a href="SCabecera?accion=editar&numFactura=<%=cabecera.getId().getNumero()%>&fechaFactura=<%=cabecera.getId().getFecha()%>" class="btn btn-info" data-num_factura="<%=cabecera.getId().getNumero()%>" data-fecha="<%=cabecera.getFechaCreacion()%>">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                        <%} %>
                    </tbody>
                </table>
                <%} else { %>
                <p>No hay facturas que mostrar</p>
                <%}%>
            </div>
        </div>
    </body>
</html>
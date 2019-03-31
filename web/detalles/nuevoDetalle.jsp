<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    Cabecera cabecera = (Cabecera) session.getAttribute("cabecera");
    ArrayList<Articulo> lstArticulo = (ArrayList<Articulo>) session.getAttribute("articulos");
    ArrayList<Detalle> lstDetalle = (ArrayList<Detalle>) session.getAttribute("detalles");
    Cliente cliente = (Cliente) session.getAttribute("cliente");

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
        <title>Nuevo Detalle - TPV Hibernate</title>
    </head>
    <body>
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <div class="form-group">
                <form id="formFactura" action="SDetalle" method="post">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="numFactura">Número Factura</label>
                                    <input class="form-control" type="text" id="numFactura" name="numFactura" value="<%=cabecera.getNumero()%>" readonly="readonly" />
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="id_cliente">Cliente</label> 
                                    <input class="form-control" type="text" id="cliente" name="cliente" value="<%=cliente.getNombre()%>" readonly="readonly" />

                                </div>
                            </div>
                            <div class="col-sm-3">
                                <label for="fecha">Fecha</label>
                                <input class="form-control" type="text" id="fechaFactura" name="fechaFactura" value="<%=cabecera.getFecha()%>" readonly="readonly" />
                            </div>
                            <!-- 					    <div class="col-sm-1"> -->
                            <!-- 					    	<p class="btnAjusta">&nbsp;</p> -->
                            <!-- 							<button type="submit" class="btn btn-primary glyphicon glyphicon-floppy-disk" name="accion" value="insertar" title="Guardar Factura"></button> -->
                            <!-- 						</div> -->
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <tr>
                            <th>Artículo</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>Importe</th>
                            <th>Acciones</th>
                        </tr>
                        <tr>
                            <td>
                                <select id="id_articulo" name="id_articulo">
                                    <option value="0"> -- Elige Artículo -- </option>
                                    <%Iterator<Articulo> itr = lstArticulo.iterator();
                                                            while (itr.hasNext()) {
                                                                Articulo articulo = (Articulo) itr.next();%>
                                    <option value="<%=articulo.getIdArticulo()%>"><%=articulo.getNombre()%></option>
                                    <%} %>
                                </select>
                            </td>
                            <td>
                                <input id="cantidad" name="cantidad" type="number" /> 
                            </td>
                            <td>
                                <input id="precio" name="precio" type="number" />
                            </td>
                            <td>
                                <p>importe linea</p>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <%Iterator<Detalle> itrDetalle = lstDetalle.iterator();
                                            while (itrDetalle.hasNext()) {
                                                Detalle detalle = (Detalle) itrDetalle.next();%>
                        <tr>
                            <td>
                                <%=detalle.getIdArticulo()%>
                            </td>
                            <td>
                                <%=detalle.getCantidad()%>
                            </td>
                            <td>
                                <%=detalle.getPrecio()%>
                            </td>
                            <td>
                                <%double importe = detalle.getCantidad() * detalle.getPrecio();%>
                                <%=importe%>
                            </td>
                            <td>
                                <p>Editar</p>
                                <p>Borrar</p>
                        </tr>						
                        <%}%>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    Cabecera cabecera = (Cabecera) session.getAttribute("cabecera");
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
        <script src="js/fnDetalles.js"></script>
        <title>Edita Detalle - TPV Hibernate</title>
    </head>
    <body>
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <form id="formFactura" action="SDetalle" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="numFactura">Número Factura</label>
                                <input class="form-control" type="text" id="numFactura" name="numFactura" value="<%=cabecera.getId().getNumero()%>" readonly="readonly" />
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="id_cliente">Cliente</label> 
                                <input class="form-control" type="text" id="cliente" name="cliente" value="<%=cabecera.getCliente().getNombre()%>" readonly="readonly" />

                            </div>
                        </div>
                        <div class="col-sm-3">
                            <label for="fecha">Fecha</label>
                            <input class="form-control" type="text" id="fechaFactura" name="fechaFactura" value="<%=cabecera.getId().getFecha()%>" readonly="readonly" />
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <tr>
                        <th>Artículo</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Importe</th>
                        <th colspan="2">Acciones</th>
                    </tr>
                    <tr>
                        <td>
                            <select id="id_articulo" class="form-control" name="id_articulo">
                                <option value="0"> -- Elige Artículo -- </option>
                                <%Iterator<Articulo> itr = lstArticulos.iterator();
                                    while (itr.hasNext()) {
                                        Articulo articulo = (Articulo) itr.next();%>
                                <option value="<%=articulo.getId()%>"><%=articulo.getNombre()%></option>
                                <%} %>
                            </select>
                        </td>
                        <td>
                            <input id="cantidad" class="form-control numeros" name="cantidad" type="number" value="1" /> 
                        </td>
                        <td>
                            <input id="precio" class="form-control numeros" name="precio" type="number" step=".01" value="0.0"/>
                        </td>
                        <td id="impLinea">
                            <input id="importe" class="form-control numeros" name="importe" type="text" value="0.0" readonly="readonly" />
                        </td>
                        <td class="acciones">
                            <button type="submit" class="btn btn-primary glyphicon glyphicon-floppy-disk" name="accion" value="insertar" title="Guardar Artículo"></button>
                        </td>
                        <td class="acciones">
                            <button type="reset" class="btn btn-danger glyphicon glyphicon-remove" title="Cancelar"></button>
                        </td>
                    </tr>
                    <%Iterator<Detalle> itrDetalle = cabecera.getDetalles().iterator();
                        DBArticulo dbArt = new DBArticulo();
                        DecimalFormat df = new DecimalFormat("###.##");
                        while (itrDetalle.hasNext()) {
                            Detalle detalle = (Detalle) itrDetalle.next();%>
                    <tr id="linea<%=detalle.getId().getOrden()%>" data-id="<%=detalle.getId().getOrden()%>" >
                        <td>
                            <input class="form-control" type="text" id="nombre<%=detalle.getId().getOrden()%>" name="nombre<%=detalle.getId().getOrden()%>" value="<%=detalle.getArticulo().getNombre()%>" readonly="readonly" />
                        </td>
                        <td>
                            <input class="form-control numeros edtCantidad" type="number" id="cantidad<%=detalle.getId().getOrden()%>" name="cantidad<%=detalle.getId().getOrden()%>" value="<%=detalle.getCantidad()%>" data-orden="<%=detalle.getId().getOrden()%>" value="<%=detalle.getCantidad()%>" />
                        </td>
                        <td>
                            <input class="form-control numeros edtPrecio" type="number" id="precio<%=detalle.getId().getOrden()%>" name="precio<%=detalle.getId().getOrden()%>" data-orden="<%=detalle.getId().getOrden()%>" step=".01"  value="<%=detalle.getPrecio()%>" />
                        </td>
                        <td>
                            <%double importe = detalle.getCantidad() * detalle.getPrecio();%>
                            <input type="text" class="form-control numeros" id="importe<%=detalle.getId().getOrden()%>" name="importe" readonly="readonly" value="<%=df.format(importe)%>" />
                        </td>
                        <td class="acciones">
                            <a href="#" data-orden="<%=detalle.getId().getOrden()%>" data-num_factura="<%=detalle.getId().getNumero()%>" data-fecha_factura="<%=detalle.getId().getFecha()%>" class="btn btn-primary btnEditar"  title="Editar Linea">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                        </td>
                        <td class="acciones">
                            <a href="#" data-orden="<%=detalle.getId().getOrden()%>" data-num_factura="<%=detalle.getId().getNumero()%>" data-fecha_factura="<%=detalle.getId().getFecha()%>" class="btn btn-danger btnBorrar"  title="Borrar Linea">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>
                        </td>
                    </tr>						
                    <%}%>
                </table>
            </form>
        </div>
    </body>
</html>
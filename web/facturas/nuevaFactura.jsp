<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="modelo.*"%>
<%@ page import="database.*"%>
<%
    List<Cabecera> lstCabecera = (List<Cabecera>) session.getAttribute("cabeceras");
    List<Cliente> lstClientes = (List<Cliente>) session.getAttribute("clientes");

    String strNumFactura = (String) session.getAttribute("numFactura");
    String fechaFactura = (String) session.getAttribute("fechaFactura");

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
        <title>TPV Hibernate</title>
    </head>
    <body>
        <div class="container">
            <%@ include file="../include/menu_alto.jsp"%>
            <div class="form-group">
                <form id="formFactura" action="SCabecera" method="post">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="numFactura">Número Factura</label>
                                    <input class="form-control" type="text" id="numFactura" name="numFactura" value="<%=strNumFactura%>" readonly="readonly" />
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="id_cliente">Cliente</label> 
                                    <select class="form-control" id="id_cliente" name="id_cliente">
                                        <option value="">-- Seleccione Cliente --</option>
                                        <%if (lstClientes.size() > 0) {
                                                Iterator<Cliente> itr = lstClientes.iterator();
                                                while (itr.hasNext()) {
                                                                                    Cliente cliente = (Cliente) itr.next();%>
                                        <option value="<%=cliente.getIdCliente()%>"><%=cliente.getNombre()%></option>
                                        <%} %>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <label for="fecha">Fecha</label>
                                <input class="form-control" type="text" id="fechaFactura" name="fechaFactura" value="<%=fechaFactura%>" readonly="readonly" />
                            </div>
                            <div class="col-sm-1">
                                <p class="btnAjusta">&nbsp;</p>
                                <button type="submit" class="btn btn-primary glyphicon glyphicon-floppy-disk" name="accion" value="insertar" title="Guardar Factura"></button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
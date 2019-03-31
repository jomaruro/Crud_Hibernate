<%-- 
    Document   : menu
    Created on : 04-oct-2018, 22:05:59
    Author     : ruiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.*"%>
<%
    Usuario usuario = new Usuario();
    if (session.getAttribute("logueado") != null) {
        usuario = ((Usuario) session.getAttribute("logueado"));
    } else {
        response.sendRedirect("index.jsp");
    }
    String nomApell = usuario.getNombre();

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
            <div class="container-fluid">
                <%if (usuario.getNivel().toUpperCase().equals("ALTO")) {%>
                <%@ include file="../include/menu_alto.jsp"%>
                <%} else {%>
                <%@ include file="../include/menu_bajo.jsp"%>
                <%}%>
            </div>
        </div>
    </body>
</html>

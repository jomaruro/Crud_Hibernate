<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.*"%>
<%
    Usuario logueado = new Usuario();
    if (session.getAttribute("logueado") != null) {
        logueado = ((Usuario) session.getAttribute("logueado"));
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<nav class="nav-site navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#nav-site-collapse">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>
            <a href="#" class="navbar-brand">TPV Hibernate</a>
        </div>
        <div class="collapse navbar-collapse" id="nav-site-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="SCabecera?accion=hoy">Facturas de hoy</a></li>
                <li><a href="SCabecera?accion=historico">Histórico</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        Mantenimiento &nbsp;
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" data-support-menu>
                        <li>
                            <a href="SArticulo?accion=listado">
                                <i class="icon icon-crown-3"></i>
                                Artículos
                            </a>
                        </li>
                        <li>
                            <a href="SCliente?accion=listado">
                                <i class="icon icon-bubble-conversation-2"></i>&nbsp; Clientes
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <%=logueado.getNombre()%>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="SLogin?accion=salir">Salir</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
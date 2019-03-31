<%-- 
    Document   : index
    Created on : 04-oct-2018, 19:51:34
    Author     : ruiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form id="formLogin" name="formLogin" action="SLogin" method="post">
                <div id="cabecera">
                    <h2>Login</h2>
                </div>
                <div class="panel_login">
                    <div class="form-group">
                        <label>Nombre:</label>
                        <input class="form-control" type="text" required placeholder="Nombre" name="nombre" size="30" value="ADMIN" />
                        <span class="form_hint">Dato obligatorio</span>
                    </div>
                    <div class="form-group">
                        <label>Clave:</label>
                        <input class="form-control" type="password" required placeholder="Clave" name="clave" size="30" value="ADMIN" />
                        <span class="form_hint">Dato obligatorio</span>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="accion" value="loguear" size="15" />
                        <button class="form-control btn btn-primary" class="submit" type="submit">Aceptar</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import database.DBUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author ruiz
 */
@WebServlet(name = "SLogin", urlPatterns = {"/SLogin"})
public class SLogin extends HttpServlet {

    private String mensajeError = "";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = null;
        String donde = "index.jsp";
        HttpSession ss = request.getSession();
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("accion").equals("") || request.getParameter("accion").equals(null)) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else if (request.getParameter("accion").equals("loguear")) {
            String nombre = request.getParameter("nombre");
            String clave = request.getParameter("clave");
            Usuario usuario = new Usuario();
            mensajeError = "";
            DBUsuario dbu = new DBUsuario();
            usuario = dbu.dameUsuario(nombre, clave);
            if (usuario.getNombre() != null && usuario.getClave() != null) {
                if (usuario.getNombre().equals(nombre) && usuario.getClave().equals(clave)) {
                    if (usuario.getNivel().equals("ALTO")) {
                        ss.setAttribute("logueado", usuario);
                        System.out.println("Usuario " + usuario.getNombre() + " Logueado");
                        donde = "menu.jsp";
                    } else if (usuario.getNivel().equals("BAJO")) {
                        ss.setAttribute("logueado", usuario);
                        System.out.println("Usuario " + usuario.getNombre() + " Logueado");
                        donde = "menu.jsp";
                    }
                } else {
                    mensajeError = "Usuario y/o password no son correctos";
                    System.err.println("Usuario " + nombre + " y/o clave " + clave + " no son correctos");
                    donde = "index.jsp";
                }
            } else {
                mensajeError = "Usuario y/o password nulos";
                System.err.println(mensajeError);
                donde = "index.jsp";
            }

            PrintWriter salidaError = response.getWriter();
            rd = request.getRequestDispatcher(donde);
            if (mensajeError.equals("")) {
                rd.forward(request, response);
            } else {
                salidaError.println("<div class='msnError'>");
                salidaError.println("<p>" + mensajeError + "</p>");
                salidaError.println("</div>");
                rd.include(request, response);
            }

        } else if (request.getParameter("accion").equals("salir")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            String nomLogueado = usuario.getNombre();
            System.out.println("Cerrada sesión del usuario " + nomLogueado);
            ss.invalidate();
            mensajeError = "";
            donde = "index.jsp";

            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

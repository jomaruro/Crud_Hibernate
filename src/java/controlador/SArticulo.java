/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import database.DBArticulo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;
import modelo.Usuario;

/**
 *
 * @author ruiz
 */
@WebServlet(name = "SArticulo", urlPatterns = {"/SArticulo"})
public class SArticulo extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = null;

        HttpSession ss = request.getSession();

        if (request.getParameter("accion").equals("") || request.getParameter("accion").equals(null)) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else if (request.getParameter("accion").equals("listado")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                DBArticulo dba = new DBArticulo();
                List<Articulo> lstArticulos = dba.listaArticulos();

                ss.setAttribute("articulos", lstArticulos);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("articulos/listaArticulos.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("insertar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                Articulo articulo = new Articulo();

                articulo.setNombre(request.getParameter("nombre"));
                articulo.setPrecio(Double.parseDouble(request.getParameter("precio")));

                DBArticulo dba = new DBArticulo();

                int idArticulo = dba.dameIdArticulo();
                idArticulo++;
                articulo.setId(idArticulo);

                if (dba.insertaArticulo(articulo)) {
                    System.out.println("Artíclo insertado correctamente");
                } else {
                    System.err.println("Artíclo NO insertado correctamente");
                }

                List<Articulo> lstArticulos = dba.listaArticulos();

                ss.setAttribute("articulos", lstArticulos);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("articulos/listaArticulos.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("editar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                Articulo articulo = new Articulo();

                articulo.setId(Integer.parseInt(request.getParameter("id_articulo")));
                articulo.setNombre(request.getParameter("nombre"));
                articulo.setPrecio(Double.parseDouble(request.getParameter("precio")));

                DBArticulo dba = new DBArticulo();

                if (dba.actualizaArticulo(articulo)) {
                    System.out.println("Artíclo modificado correctamente");
                } else {
                    System.err.println("Artíclo NO modificado correctamente");
                }
                List<Articulo> lstArticulos = dba.listaArticulos();

                ss.setAttribute("articulos", lstArticulos);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("articulos/listaArticulos.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("damePrecio")) {
            int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
            double precio = 0;
            DBArticulo dbArt = new DBArticulo();
            precio = dbArt.damePrecio(idArticulo);

            PrintWriter salida = null;
            try {
                salida = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            salida.println(precio);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import database.DBCliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Usuario;

/**
 *
 * @author ruiz
 */
@WebServlet(name = "SCliente", urlPatterns = {"/SCliente"})
public class SCliente extends HttpServlet {

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
        RequestDispatcher rd = null;

        HttpSession ss = request.getSession();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("accion").equals("") || request.getParameter("accion").equals(null)) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else if (request.getParameter("accion").equals("listado")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            List<Cliente> lstCliente = null;
            if (usuario != null) {

                DBCliente dbc = new DBCliente();
                lstCliente = dbc.listaClientes();

                ss.setAttribute("clientes", lstCliente);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("clientes/listaClientes.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("insertar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {
                Cliente cliente = new Cliente();

                cliente.setNombre(request.getParameter("nombre"));
                cliente.setCif(request.getParameter("cif"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setLocalidad(request.getParameter("localidad"));
                cliente.setProvincia(request.getParameter("provincia"));

                DBCliente dbc = new DBCliente();

                int idCliente = dbc.dameIdCliente();
                idCliente++;
                cliente.setIdCliente(idCliente);

                if (dbc.insertaCliente(cliente)) {
                    System.out.println("Cliente insertado correctamente");
                } else {
                    System.err.println("Cliente NO insertado correctamente");
                }

                List<Cliente> lstCliente = dbc.listaClientes();

                ss.setAttribute("clientes", lstCliente);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("clientes/listaClientes.jsp");
                rd.forward(request, response);

            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("editar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {
                Cliente cliente = new Cliente();

                cliente.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));
                cliente.setNombre(request.getParameter("nombre"));
                cliente.setCif(request.getParameter("cif"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setLocalidad(request.getParameter("localidad"));
                cliente.setProvincia(request.getParameter("provincia"));

                DBCliente dbc = new DBCliente();

                if (dbc.actualizaCliente(cliente)) {
                    System.out.println("Cliente actualizado correctamente");
                } else {
                    System.err.println("Cliente NO actualizado correctamente");
                }

//			   	ArrayList<Cliente> lstCliente = dbc.listaClientes();
//			   	
//			   	ss.setAttribute("clientes", lstCliente);
//			   	ss.setAttribute("logueado", usuario);
//			   	rd = request.getRequestDispatcher("clientes/listaClientes.jsp");
//				rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
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

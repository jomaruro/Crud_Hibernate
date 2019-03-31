/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import database.DBArticulo;
import database.DBCabecera;
import database.DBCliente;
import database.DBDetalle;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Articulo;
import modelo.Cabecera;
import modelo.CabeceraId;
import modelo.Cliente;
import modelo.Detalle;
import modelo.Usuario;

/**
 *
 * @author ruiz
 */
@WebServlet(name = "SCabecera", urlPatterns = {"/SCabecera"})
public class SCabecera extends HttpServlet {

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
        RequestDispatcher rd;
        HttpSession ss = request.getSession();
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("accion").equals("") || request.getParameter("accion") == null) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);

        } else if (request.getParameter("accion").equals("hoy")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                Date hoy = new Date();
                String sf = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(sf);
                String fechaFactura = sdf.format(hoy);
                System.out.println("SCabecera - hoy -> " + fechaFactura);
                DBCabecera dbCab = new DBCabecera();
                List<Cabecera> lstCabeceras = dbCab.listaFacturas(fechaFactura);

                ss.setAttribute("cabeceras", lstCabeceras);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("facturas/listaFacturas.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("historico")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                DBCabecera dbc = new DBCabecera();
                List<Cabecera> lstCabecera = dbc.listaHistorico();

                ss.setAttribute("cabeceras", lstCabecera);
                ss.setAttribute("logueado", usuario);
                rd = request.getRequestDispatcher("facturas/listaHistorico.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("nueva")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                DBCliente dbc = new DBCliente();
                List<Cliente> lstClientes = dbc.listaClientes();

                DBCabecera dbCab = new DBCabecera();
                int numFactura = dbCab.dameNumFactura();
                numFactura++;
                String strNumFactura = String.valueOf(numFactura);

                Date hoy = new Date();
                String sf = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(sf);
                String fechaFactura = sdf.format(hoy);

                ss.setAttribute("logueado", usuario);
                ss.setAttribute("clientes", lstClientes);
                ss.setAttribute("numFactura", strNumFactura);
                ss.setAttribute("fechaFactura", fechaFactura);
                rd = request.getRequestDispatcher("facturas/nuevaFactura.jsp");
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("insertar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                Cabecera cabecera = new Cabecera();
                CabeceraId cabeceraId = new CabeceraId();
                cabeceraId.setNumero(Integer.parseInt(request.getParameter("numFactura")));
                cabeceraId.setFecha(request.getParameter("fechaFactura"));
                
                int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                
                Cliente cliente;
                DBCliente dbCliente = new DBCliente();
                cliente = dbCliente.dameCliente(id_cliente);
                cabecera.setId(cabeceraId);
                cabecera.setCliente(cliente);
                cabecera.setUsuario(usuario);

                DBCabecera dbCab = new DBCabecera();
                if (dbCab.insertaCabecera(cabecera)) {
                    System.out.println("Inserción correcta de cabecera");
                    DBArticulo dbArt = new DBArticulo();
                    List<Articulo> lstArticulos = dbArt.listaArticulos();

//                    DBCliente dbCli = new DBCliente();
//                    cliente = dbCli.dameCliente(cabecera.getCliente());

                    List<Detalle> lstDetalles = null;

                    ss.setAttribute("logueado", usuario);
                    ss.setAttribute("cabecera", cabecera);
                    ss.setAttribute("detalles", lstDetalles);
                    ss.setAttribute("cliente", cliente);
                    ss.setAttribute("articulos", lstArticulos);

                    rd = request.getRequestDispatcher("detalles/editaDetalles.jsp");
                    rd.forward(request, response);
                } else {
                    System.err.println("Inserción INcorrecta de cabecera");
                    rd = request.getRequestDispatcher("facturas/nuevaFactura.jsp");
                    rd.forward(request, response);
                }

            } else {
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("accion").equals("editar")) {
            Usuario usuario = (Usuario) ss.getAttribute("logueado");
            if (usuario != null) {

                Cabecera cabecera;
                int numFactura = Integer.parseInt(request.getParameter("numFactura"));
                String fechaFactura = request.getParameter("fechaFactura");

                DBCabecera dbCab = new DBCabecera();
                CabeceraId cabeceraId = new CabeceraId();
                cabeceraId.setNumero(numFactura);
                cabeceraId.setFecha(fechaFactura);
                cabecera = dbCab.dameFactura(cabeceraId);
                Cliente cliente = new Cliente();
                cliente = cabecera.getCliente();

                DBArticulo dbArt = new DBArticulo();
                List<Articulo> lstArticulo = dbArt.listaArticulos();
                
                ss.setAttribute("logueado", usuario);
                ss.setAttribute("cabecera", cabecera);
                ss.setAttribute("cliente", cliente);
                ss.setAttribute("articulos", lstArticulo);

                rd = request.getRequestDispatcher("detalles/editaDetalles.jsp");
                rd.forward(request, response);

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

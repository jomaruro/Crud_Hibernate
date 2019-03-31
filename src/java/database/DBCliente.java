/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import hibernate.HibernateUtil;
import java.sql.Connection;
import java.util.List;
import modelo.Cliente;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ruiz
 */
public class DBCliente {

    public List<Cliente> listaClientes() {
        List<Cliente> lstClientes = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "from Cliente";
            Query query = session.createQuery(sql);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lstClientes = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CLIENTE => " + e.getMessage());
        }
        System.err.println("DBCliente - listaClientes => " + lstClientes.size());
        return lstClientes;
    }    
    
    public int dameIdCliente() {
        int idCliente = 0;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "SELECT MAX(id.idCliente) FROM Cliente";
            Query query = session.createQuery(sql);
            idCliente = (int) query.uniqueResult();
            System.out.println("DBCliente -> dameIdCliente - idCliente -> " + idCliente);
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CLIENTE => " + e.getMessage());
        }
        return idCliente;
    }
    
    public boolean insertaCliente(Cliente cliente) {
        boolean exito = false;

        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.save(cliente);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla CLIENTE => " + e.getMessage());
        }
        return exito;
    }
    
    public boolean actualizaCliente(Cliente cliente) {
        boolean exito = false;

        Connection con = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();
            session.saveOrUpdate(cliente);
            trns.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al actualizar la tabla CLIENTE => " + e.getMessage());
        }
        return exito;
    }
    
    public Cliente dameCliente(int id_cliente) {
        Cliente cliente = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "FROM Cliente WHERE id_cliente = :id_cliente";
            Query query = session.createQuery(sql);
            query.setParameter("id_cliente", id_cliente);
            
            cliente = (Cliente) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CLIENTE => " + e.getMessage());
        }
        return cliente;
    }
}

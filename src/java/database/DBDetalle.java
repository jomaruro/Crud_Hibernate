/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import hibernate.HibernateUtil;
import java.util.List;
import modelo.CabeceraId;
import modelo.Detalle;
import modelo.DetalleId;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ruiz
 */
public class DBDetalle {
    
    public int dameOrden(int numFactura, String fechaFactura) {
        int orden = 0;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "SELECT MAX(id.orden) FROM Detalle WHERE id.numero = :numFactura AND id.fecha LIKE :fechaFactura";
            Query query = session.createQuery(sql);
            query.setParameter("numFactura", numFactura);
            query.setParameter("fechaFactura", fechaFactura);
            if (query.uniqueResult() != null) {
                orden = (int) query.uniqueResult();
            }
            System.out.println("DBDetalle - dameOrden - orden -> " + orden);
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla DETALLE => " + e.getMessage());
        }
        return orden;
    }
    
    public boolean insertaDetalle(Detalle detalle) {
        boolean exito = false;
        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.save(detalle);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla DETALLE => " + e.getMessage());
        }
        return exito;
    }
    
    public boolean actualizaDetalle(Detalle detalle) {
        boolean exito = false;
        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.saveOrUpdate(detalle);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla DETALLE => " + e.getMessage());
        }
        return exito;
    }
    
    public boolean borraDetalle(DetalleId detalleId) {
        boolean exito = false;
        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.delete(detalleId);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla DETALLE => " + e.getMessage());
            trans.rollback();
        }
        return exito;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import hibernate.HibernateUtil;
import java.sql.Connection;
import java.util.List;
import modelo.Articulo;
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
public class DBArticulo {

    public List<Articulo> listaArticulos() {
        List<Articulo> lstArticulos = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "from Articulo";
            Query query = session.createQuery(sql);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lstArticulos = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla ARTICULO => " + e.getMessage());
        }
        return lstArticulos;
    }

    public int dameIdArticulo() {
        int idArticulo = 0;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "SELECT MAX(id.id) FROM Articulo";
            Query query = session.createQuery(sql);
            idArticulo = (int) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla ARTICULO => " + e.getMessage());
        }
        return idArticulo;
    }

    public boolean insertaArticulo(Articulo articulo) {
        boolean exito = false;

        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.save(articulo);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla ARTICULO => " + e.getMessage());
        }
        return exito;
    }

    public boolean actualizaArticulo(Articulo articulo) {
        boolean exito = false;

        Connection con = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();
            session.saveOrUpdate(articulo);
            trns.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al actualizar la tabla ARTICULO => " + e.getMessage());
        }
        return exito;
    }

    public double damePrecio(int idArticulo) {
        double precio = 0;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "select precio FROM Articulo where id_articulo = :idArticulo";
            Query query = session.createQuery(sql);
            query.setParameter("idArticulo", idArticulo);
            precio = (double) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla ARTICULO => " + e.getMessage());
        }
        return precio;
    }

    public Articulo dameArticulo(int id_articulo) {
        Articulo articulo = new Articulo();
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "FROM Articulo WHERE id_articulo = :id_articulo";
            Query query = session.createQuery(sql);
            query.setParameter("id_articulo", id_articulo);
            articulo = (Articulo) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla ARTICULO => " + e.getMessage());
        }
        return articulo;
    }
}

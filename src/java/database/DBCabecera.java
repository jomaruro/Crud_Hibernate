/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import hibernate.HibernateUtil;
import java.util.List;
import modelo.Cabecera;
import modelo.CabeceraId;
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
public class DBCabecera {

    public List<Cabecera> listaFacturas(String hoy) {
        List<Cabecera> lstCabeceras = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "from Cabecera where fecha like :fecha";
            Query query = session.createQuery(sql);
            query.setParameter("fecha", hoy);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lstCabeceras = query.list();
            System.out.println("DBCabecera - listaFacturas -> " + lstCabeceras.size());
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CABECERA => " + e.getMessage());
        }
        return lstCabeceras;
    }

    public List<Cabecera> listaHistorico() {
        List<Cabecera> lstCabeceras = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "from Cabecera order by to_date(fecha, 'dd/mm/yyyy') desc";
            Query query = session.createQuery(sql);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            lstCabeceras = query.list();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CABECERA => " + e.getMessage());
        }
        return lstCabeceras;
    }

    public int dameNumFactura() {
        int numFactura = 0;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "SELECT MAX(NUMERO) FROM CABECERA WHERE EXTRACT(YEAR FROM FECHA_CREACION) LIKE EXTRACT(YEAR FROM SYSDATE)";
            Query query = session.createQuery(sql);
            numFactura = (int) query.uniqueResult();
            System.out.println("DBCabecera - dameNumFactura -> " + numFactura);
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CABECERA => " + e.getMessage());
        }
        return numFactura;
    }
    
    public boolean insertaCabecera(Cabecera cabecera) {
        boolean exito = false;
        Transaction trans = null;
        Session sesion = null;
        SessionFactory sesionFactory = null;

        try {
            sesionFactory = HibernateUtil.getSessionFactory();
            sesion = sesionFactory.openSession();
            trans = sesion.beginTransaction();
            sesion.save(cabecera);
            trans.commit();
            exito = true;
        } catch (HibernateException e) {
            System.err.println("Error al insertar en la tabla CABECERA => " + e.getMessage());
        }
        return exito;
    }
    
    public Cabecera dameFactura(CabeceraId cabeceraId) {
        Cabecera cabecera = new Cabecera();
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "FROM Cabecera WHERE NUMERO = :numero AND FECHA LIKE :fecha";
            Query query = session.createQuery(sql);
            query.setParameter("numero", cabeceraId.getNumero());
            query.setParameter("fecha", cabeceraId.getFecha());
            cabecera = (Cabecera) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla CABECERA => " + e.getMessage());
        }        
        return cabecera;
    }
}

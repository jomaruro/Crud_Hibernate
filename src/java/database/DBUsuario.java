/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import hibernate.HibernateUtil;
import java.sql.Connection;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ruiz
 */
public class DBUsuario {
    
    public Usuario dameUsuario(String nombre, String clave) {
        Usuario usuario = null;
        Connection con = null;
        Transaction trns = null;
        Session session = null;
        SessionFactory sf = null;

        try {
            sf = HibernateUtil.getSessionFactory();
            session = sf.openSession();
            trns = session.beginTransaction();

            String sql = "from Usuario where NOMBRE LIKE :parametro1 AND CLAVE LIKE :parametro2";
            Query query = session.createQuery(sql);
            query.setParameter("parametro1", nombre);
            query.setParameter("parametro2", clave);
            
            usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error al leer la tabla USUARIO => " + e.getMessage());
        }        
        return usuario;
    }
}

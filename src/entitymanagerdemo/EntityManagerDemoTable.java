/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;

/**
 *
 * @author theki
 */
public class EntityManagerDemoTable {

    public static void insertCustomerAddress(Customer std, Address adr) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(std);
            em.persist(adr);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static List<Customer> findAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Customer> cusList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        em.close();
        return cusList;
    }

    public static List<Customer> findCustomerByCity(String city) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        String sql="SELECT c.* FROM CUSTOMER c  LEFT JOIN ADDRESS a ON c.id=a.CUSTOMER_FK WHERE city=?city";
        Query query = em.createNativeQuery(sql, Customer.class);
        query.setParameter("city", city);
        List<Customer> cusList = (List<Customer>) query.getResultList();
        em.close();
        return cusList;
    }

    public static Address findCustomerById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Address adr = em.find(Address.class, id);
        em.close();
        return adr;
    }

    public static Address findAddressById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        Address adr = em.find(Address.class, id);
        em.close();
        return adr;
    }
}

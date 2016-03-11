/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import person.IPersonFacade;


/**
 *
 * @author Kristian Nielsen
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestEx02PU");
    EntityManager em = emf.createEntityManager();
    
    
    @Override
    public Person addperson(Person p) {
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        
        return (Person) em.createNamedQuery("Person.findById").setParameter("id", p.getId()).getSingleResult();

    }

    @Override
    public Person deletePerson(int id) {
        Person p = (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        return (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public Person getPerson(int id) {
        return (Person) em.createNamedQuery("Person.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Person> getPersons() {
        return em.createNamedQuery("Person.findAll").getResultList();
    }

    @Override
    public Person editPerson(Person p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        
        return em.find(Person.class, p);
    }
    
}

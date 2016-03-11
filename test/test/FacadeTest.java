/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Person;
import facade.PersonFacade;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Nielsen
 */
public class FacadeTest {
    
    
    
    public FacadeTest() {
    }
    
    @Test
    public void runTest(){
        PersonFacade pf = new PersonFacade();
        List<Person> persons = pf.getPersons();
        Person p = new Person();
        p.setFname("Test");
        p.setLname("Test");
        p.setPhone("testest");
        
        int beforeCount = persons.size();
        
        Person p2 = pf.addperson(p);
        
        persons = pf.getPersons();
                
        
        int afterCount = persons.size();
        
        assertEquals(beforeCount+1, afterCount);
        
        pf.deletePerson(p2.getId());
        
        
        
        
        
    }
}

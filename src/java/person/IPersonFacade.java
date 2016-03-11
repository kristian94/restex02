/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package person;

import entity.Person;
import java.util.List;

/**
 *
 * @author Kristian Nielsen
 */
public interface IPersonFacade {
    public Person addperson(Person p);
    public Person deletePerson(int id);
    public Person getPerson(int id);
    public List<Person> getPersons();
    public Person editPerson(Person p);
}

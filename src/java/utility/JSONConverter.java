/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import entity.Person;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kristian Nielsen
 */
public class JSONConverter {

    private static Gson gson = new Gson();
    
    public static Person getPersonFromJson(String json){
        return gson.fromJson(json, Person.class);
    }
    
    public static String getJSONFromPerson(Person p){
        return gson.toJson(p);
    }
    
    public static List<Person> getPersonsFromJson(){
        return new ArrayList<Person>();
    }
    
    public static String getJSONFromPersons(List<Person> persons){
        
        return gson.toJson(persons);
    }

}

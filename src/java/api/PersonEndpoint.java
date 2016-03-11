/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entity.Person;
import facade.PersonFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import utility.JSONConverter;

/**
 * REST Web Service
 *
 * @author Kristian Nielsen
 */
@Path("person")
public class PersonEndpoint {

    PersonFacade pf = new PersonFacade();
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonEndpoint
     */
    public PersonEndpoint() {
    }

    /**
     * Retrieves representation of an instance of api.PersonEndpoint
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getPersons() {
        return JSONConverter.getJSONFromPersons(pf.getPersons());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPersonById(@PathParam("id") int id) {
        Person p = pf.getPerson(id);
        return JSONConverter.getJSONFromPerson(p);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String json){
        Person p = JSONConverter.getPersonFromJson(json);
        Person p2 = pf.addperson(p);
        return JSONConverter.getJSONFromPerson(p2);
    }
    
    /**
     * PUT method for updating or creating an instance of PersonEndpoint
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String editPerson(String content) {
        Person p = JSONConverter.getPersonFromJson(content);
        Person p2 = pf.editPerson(p);
        return JSONConverter.getJSONFromPerson(p2);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id) {
        Person p = pf.deletePerson(id);
        return JSONConverter.getJSONFromPerson(p);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Kristian Nielsen
 */

@Provider
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

    @Override
    public Response toResponse(PersonNotFoundException e) {
        return Response.status(404).entity(
                "{\"code\": 404, \"message\": \"Person could not be found.\"}"
        ).type(MediaType.APPLICATION_JSON).build();
    }
    
}

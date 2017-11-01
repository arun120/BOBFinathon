/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTful;


import Database.TransactionDetails;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Fluffy
 */
@Path("Beneficiary")
public class BeneficiaryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BeneficiaryResource
     */
    public BeneficiaryResource() {
    }

    /**
     * Retrieves representation of an instance of RESTful.BeneficiaryResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException("Operation Not Supported");
    }
    
    @POST
    @Path("{acnumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String body,@PathParam("acnumber")String acnumber) {
        System.out.println(acnumber+" "+body);
        
        TransactionDetails td=TransactionDetails.fromJson(body);
        td.setSaccount(acnumber);
        
        
        Response r=new Response();
        r.reponse=td.insert();
        return r;
    }
    /**
     * PUT method for updating or creating an instance of BeneficiaryResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
        throw new UnsupportedOperationException();
    }
}

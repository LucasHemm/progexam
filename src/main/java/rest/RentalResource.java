package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RentalDTO;
import dtos.UserDTO;
import entities.User;
import facades.RentalFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("rental")
public class RentalResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final RentalFacade FACADE =  RentalFacade.getRentalFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentals(){
        List<RentalDTO> rentalDTOS = FACADE.getAllRentals();
        return Response.ok().entity(rentalDTOS).build();
    }




}
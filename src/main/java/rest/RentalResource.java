package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.HouseDTO;
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

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRental(String input){

        System.out.println(input);
       HouseDTO houseDTO = GSON.fromJson(input, HouseDTO.class);
       UserDTO userDTO = GSON.fromJson(input, UserDTO.class);
       RentalDTO rentalDTO = GSON.fromJson(input,RentalDTO.class);
        System.out.println(houseDTO);
        System.out.println(userDTO);
        System.out.println(rentalDTO);


        RentalDTO createdRentalDTO = FACADE.createRental(houseDTO, userDTO, rentalDTO);
        System.out.println(createdRentalDTO);
        return Response.ok().entity(createdRentalDTO).build();
    }

    @PUT
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTenant(String input){

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(input, JsonObject.class);

        String newTenant = jsonObject.get("newTenant").getAsString();
        Long id = jsonObject.get("id").getAsLong();

        RentalDTO switchedRentalDTO = FACADE.addTenant(newTenant,id);
        return Response.ok().entity(switchedRentalDTO).build();
    }

    @PUT
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTenant(String input){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(input, JsonObject.class);

        String oldTenant = jsonObject.get("oldTenant").getAsString();
        Long id = jsonObject.get("id").getAsLong();

        RentalDTO switchedRentalDTO = FACADE.removeTenant(oldTenant,id);
        return Response.ok().entity(switchedRentalDTO).build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAll(String input){
        RentalDTO rentalDTO = GSON.fromJson(input, RentalDTO.class);
        System.out.println(rentalDTO);


        JsonObject jsonObject = GSON.fromJson(input, JsonObject.class);

        Long id = jsonObject.get("id").getAsLong();


        RentalDTO updatedRentalDTO = FACADE.updateAll(rentalDTO,id);
        return Response.ok().entity(updatedRentalDTO).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRental(@PathParam("id") Long id){
        FACADE.deleteRental(id);
        return Response.ok().build();
    }




}
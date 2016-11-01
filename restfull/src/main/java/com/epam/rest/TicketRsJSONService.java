package com.epam.rest;

import entity.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/TicketJSONService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketRsJSONService extends TicketService {


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/getTicket/{id}")
    public Ticket getTicket(@PathParam("id") Integer id) {
        return tickets.get(id);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/bookedTicket")

    public Response bookedTicket(Ticket innerTicket) {

        return super.bookedTicket(innerTicket);
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/payTicket")
    public Response payTicket(Ticket innerTicket) {

        return super.payTicket(innerTicket);
    }


    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/removeTicket/{id}")
    public Response removeTicket(@PathParam("id") Integer numberTicket) {
        return super.removeTicket(numberTicket);
    }


}
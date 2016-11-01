package com.epam.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Human;
import entity.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */
@Path("/TicketXMLService")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class TicketXMLService extends TicketService{



    @GET
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/getTicket/{id}")
    public Ticket getTicket(@PathParam("id") Integer id) {
        return super.getTicket(id);
    }

    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/bookedTicket")

    public Response bookedTicket(Ticket innerTicket) {

        return super.bookedTicket(innerTicket);
    }


    @PUT
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/payTicket")
    public Response payTicket(Ticket innerTicket) {

        return super.payTicket(innerTicket);
    }


    @DELETE
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    @Path("/removeTicket/{id}")
    public Response removeTicket(@PathParam("id")  Integer numberTicket) {
              return super.removeTicket(numberTicket);
    }

}

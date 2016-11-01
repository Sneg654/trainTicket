package com.epam.rest;

import entity.Ticket;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public abstract class TicketService {
    public static int currentNumber = 100;
    protected static final String BOOKED = "BOOKED";
    protected static final String PAID = "PAID";
    protected static Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    static {
    tickets= StartCollection.getCollection(tickets);
    }

    public Ticket getTicket(Integer id) {
        return tickets.get(id);
    }


    public Response bookedTicket(Ticket innerTicket) {
        Ticket ticket = new Ticket();
        ticket.setStartCity(innerTicket.getStartCity());
        ticket.setFinishCity(innerTicket.getFinishCity());
        ticket.setCostTicket(5700d);
        ticket.setArrivaDate(innerTicket.getArrivaDate());
        ticket.setDepartureDate(innerTicket.getDepartureDate());
        ticket.setStatusTicket(BOOKED);
        ticket.setHuman(innerTicket.getHuman());
        ticket.setNumberTicket(currentNumber++);
        tickets.put(ticket.getNumberTicket(), ticket);
        return Response.ok(ticket.getNumberTicket().toString()).build();
    }

    public Response payTicket(Ticket innerTicket) {
        Double summ = 0d;
        String returnMessage;
        Ticket ticket = tickets.get(innerTicket.getNumberTicket());
        if (ticket != null) {
            summ = innerTicket.getCostTicket() - ticket.getCostTicket();
            if (summ >= 0) {
                ticket.setStatusTicket(PAID);
                tickets.put(innerTicket.getNumberTicket(), ticket);
                returnMessage = "ticked #" + innerTicket.getNumberTicket() + " paid, delivery " + summ;
            } else {
                returnMessage = "you have insufficient funds";
            }
        } else {
            returnMessage = "ticket not found";
        }
        return Response.ok(returnMessage).build();
    }


    public Response removeTicket(Integer numberTicket) {
        String returnMessage;
        Ticket ticket = tickets.remove(numberTicket);
        if (ticket != null) {
            returnMessage = "ticket was removed";
        } else {
            returnMessage = "ticket #" + numberTicket + " not found";
        }

        return Response.ok(returnMessage).build();
    }


}

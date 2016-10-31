package com.epam.rest;

import entity.Human;
import entity.Ticket;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public abstract class TicketService{
    protected static int currentNumber = 100;
    protected static final String BOOKED = "BOOKED";
    protected static final String PAID = "PAID";
    protected static Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();
    static {
        Ticket ticket1 =new Ticket();
        ticket1.setStartCity("Almaty");
        ticket1.setFinishCity("Astana");
        ticket1.setDepartureDate(new Date());
        ticket1.setArrivaDate(new Date());
        ticket1.setCostTicket(3500d);
        ticket1.setNumberTicket(currentNumber++);
        ticket1.setStatusTicket(BOOKED);
        Human human1=new Human();
        human1.setFirstName("Jonh");
        human1.setLastName("Smith");
        human1.setMidlleName("Ivanovich");
        human1.setBirthday(new Date(38,11,6));
        ticket1.setHuman(human1);
        tickets.put(ticket1.getNumberTicket(),ticket1);

        Ticket ticket2 =new Ticket();
        ticket2.setStartCity("Almaty");
        ticket2.setFinishCity("Moscow");
        ticket2.setDepartureDate(new Date());
        ticket2.setArrivaDate(new Date());
        ticket2.setCostTicket(7500d);
        ticket2.setNumberTicket(currentNumber++);
        ticket2.setStatusTicket(BOOKED);
        Human human2=new Human();
        human2.setFirstName("Dasha");
        human2.setLastName("Smith");
        human2.setMidlleName("Ivanovna");
        human2.setBirthday(new Date(68,10,16));
        ticket2.setHuman(human2);
        tickets.put(ticket2.getNumberTicket(),ticket2);

        Ticket ticket3 =new Ticket();
        ticket3.setStartCity("Karaganda");
        ticket3.setFinishCity("Moscow");
        ticket3.setDepartureDate(new Date());
        ticket3.setArrivaDate(new Date());
        ticket3.setCostTicket(17500d);
        ticket3.setNumberTicket(currentNumber++);
        ticket3.setStatusTicket(PAID);
        Human human3=new Human();
        human3.setFirstName("Dasha");
        human3.setLastName("Ivanova");
        human3.setMidlleName("Ivanovna");
        human3.setBirthday(new Date(29,6,16));
        ticket3.setHuman(human3);
        tickets.put(ticket3.getNumberTicket(),ticket3);
    }

    public Ticket getTicket(Integer id) {
        return tickets.get(id);
    }



    public Response bookedTicket(Ticket innerTicket)  {
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
        return  Response.ok( ticket.getNumberTicket()).build();
    }

    public Response payTicket(Ticket innerTicket) {
        Double summ = 0d;
        String returnMessage;
        Ticket ticket = tickets.get(innerTicket.getNumberTicket());
        if (ticket != null) {
            summ = innerTicket.getCostTicket() - ticket.getCostTicket();
            if (summ >= 0) {
                ticket.setStatusTicket(PAID);
                tickets.put(innerTicket.getNumberTicket(),ticket);
                returnMessage = "ticked #" + innerTicket.getNumberTicket() + " paid, delivery " + summ;
            } else {
                returnMessage = "you have insufficient funds";
            }
        } else {
            returnMessage = "ticket not found";
        }
        return Response.ok( returnMessage).build();
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

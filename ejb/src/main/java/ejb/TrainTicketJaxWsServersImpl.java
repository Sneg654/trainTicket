package ejb;

import entity.Human;
import entity.Ticket;
import start.StartCollection;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */
@WebService(endpointInterface = "ejb.TrainTicketJaxWsServers")
@Stateless
public class TrainTicketJaxWsServersImpl implements TrainTicketJaxWsServers {
    public static int currentNumber = 100;
    public static final String BOOKED = "BOOKED";
    public static final String PAID = "PAID";
    private static Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();
    static {
     tickets= StartCollection.getCollection(tickets);
    }
    @WebMethod
    public Integer bookedTicket(@WebParam(name ="startCity")String startCity,
                                @WebParam(name ="endCity") String endCity,
                                @WebParam(name ="startDate")Date startDate,
                                @WebParam(name ="endDate") Date endDate,
                                @WebParam(name ="human")Human human) {
        Ticket ticket = new Ticket();
        ticket.setStartCity(startCity);
        ticket.setFinishCity(endCity);
        ticket.setCostTicket(5700d);
        ticket.setArrivaDate(endDate);
        ticket.setDepartureDate(startDate);
        ticket.setStatusTicket(BOOKED);
        ticket.setHuman(human);
        ticket.setNumberTicket(currentNumber++);
        tickets.put(ticket.getNumberTicket(), ticket);
        return ticket.getNumberTicket();
    }

    public Ticket findTicket(Integer numberTicket) {
        return tickets.get(numberTicket);
    }

    @WebMethod
    public String payTicket(@WebParam(name = "numberTicket") Integer numberTicket, @WebParam(name = "money") Double money) {
        Double summ = 0d;
        String returnMessage;
        Ticket ticket = tickets.get(numberTicket);
        if (ticket != null) {
            summ = money - ticket.getCostTicket();
            if (summ >= 0) {
                ticket.setStatusTicket(PAID);
                returnMessage = "ticked #" + numberTicket + " paid, delivery " + summ;
            } else {
                returnMessage = "you have insufficient funds";
            }
        } else {
            returnMessage = "ticket not found";
        }
        return returnMessage;
    }



    @WebMethod
    public String removeTicket(@WebParam(name = "numberTicket") Integer numberTicket) {
        String returnMessage;
        Ticket ticket = tickets.remove(numberTicket);
        if (ticket != null) {
            returnMessage = "ticket was removed";
        } else {
            returnMessage = "ticket #" + numberTicket + " not found";
        }

        return returnMessage;
    }


}

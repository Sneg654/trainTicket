package ejb;

import entity.Human;
import entity.Ticket;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */

//Задача 1. Используя JAX-WS создать веб-сервис, предоставляющий следующие операции над билетом на поезд,
// описанные во введении: забронировать билет на поезд, получить билет по номеру, оплатить билет на поезд,
// вернуть билет на поезд. Также, создать клиент, демонстрирующий использование веб-сервиса.


@WebService(name = "TicketServer")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

public interface TrainTicketJaxWsServers {

    @WebMethod
    Integer bookedTicket(@WebParam(name = "startCity") String startCity,
                         @WebParam(name = "endCity") String endCity,
                         @WebParam(name = "startDate") Date startDate,
                         @WebParam(name = "endDate") Date endDate,
                         @WebParam(name = "human") Human human);

    @WebMethod
    Ticket findTicket(@WebParam(name = "numberTicket") Integer numberTicket);

    @WebMethod
    String payTicket(@WebParam(name = "numberTicket") Integer numberTicket, @WebParam(name = "money") Double money);

    @WebMethod
    String removeTicket(@WebParam(name = "numberTicket") Integer numberTicket);

}

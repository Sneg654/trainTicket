import com.sun.jersey.api.client.ClientResponse;
import ejb.TrainTicketJaxWsServers;
import entity.Human;
import entity.Ticket;

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public class WSClient {
    public static void main(String[] args) {
        try{
            QName serviceName = new QName("http://ejb/", "TrainTicketJaxWsServersImplService");
            URL wsdlURL = new URL("http://epkzkarw0338.moscow.epam.com:45054/TrainTicketEjb/TrainTicketJaxWsServersImpl?wsdl");
            Service service = Service.create(wsdlURL, serviceName);
            TrainTicketJaxWsServers proxy = (TrainTicketJaxWsServers) service.getPort(TrainTicketJaxWsServers.class);
            Integer i= proxy.bookedTicket("34","4334",new Date(),new Date(),new Human());
            Scanner sc = new Scanner(System.in);
            System.out.println(RsClient.CHOISE_COMMAND);
            while (sc.hasNextLine()) {
                try {
                    String[] arguments = sc.nextLine().replaceAll(" ", "").split(",");
                    if (arguments[0].equalsIgnoreCase("gt")) {
                        Ticket ticket=proxy.findTicket(Integer.valueOf(arguments[1]));

                        System.out.println(ticket);
                    } else if (arguments[0].equalsIgnoreCase("rt")) {

                       String message=proxy.removeTicket(Integer.valueOf(arguments[1]));
                            System.out.println(message);
                    } else if (arguments[0].equalsIgnoreCase("pt")) {
                        String message=proxy.payTicket(Integer.valueOf(arguments[1]),Double.valueOf(arguments[2]));
                        System.out.println(message);
                    } else if (arguments[0].equalsIgnoreCase("bt")) {


                       Integer number=proxy.bookedTicket(arguments[5],
                                arguments[6],
                               Utils.getDate(arguments[7]),
                               Utils.getDate(arguments[8]),
                               Utils.getHuman(arguments[1],
                                       arguments[2],
                                       arguments[3],
                                       Utils.getDate(arguments[4])));

                        System.out.println(number);
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You enter incorrect count args for this command");
                }catch (ParseException e){
                    System.out.println("your enter inccorect atribute");
                }
            }

            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

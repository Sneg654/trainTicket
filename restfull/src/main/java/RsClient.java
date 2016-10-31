/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import entity.Ticket;

import java.text.ParseException;
import java.util.Scanner;

public class RsClient {
    private static final String baseURL = "http://localhost:8095/rest/";
    private static final String JSON_SERVER = "TicketJSONService";
    private static final String XML_SERVER = "TicketXMLService";
    private static final String GET = "/getTicket/";
    private static final String PUT = "/payTicket";
    private static final String DELETE = "/removeTicket/";
    private static final String POST = "/bookedTicket";
    public static final String CHOISE_SERVERSE = "Please choise type of web serverse(Example 1: JSON, Example 2: XML)";
    public static final String JSON = "JSON";
    public static final String XML = "XML";
    public static final String CHOISE_COMMAND = "enter command , for use serverse Exampes:\n" +
            "gt,100- for show informaion about ticket with number 100\n" +
            "rt,100- for delete  ticket with number 100\n" +
            "pt,100,5000- for payment  ticket with number 100, sum 5000\n" +
            "bt,FirstName,LastName,MiddleName,BirthDay(format:DD.MM.YYYY),ArrivaCity,DepartureCity,AriveDate(format:DD.MM.YYYY),DepartureDate(format:DD.MM.YYYY)\n";


    //    private static final String baseUrl = "http://localhost:8095/rest/TicketJSONService/payTicket";
//    private static final String baseUrl = "http://localhost:8095/rest/TicketJSONService/payTicket";
//    private static final String baseUrl = "http://localhost:8095/rest/TicketJSONService/getTicket/101";
    private static final String baseUrl = "http://localhost:8095/rest/TicketJSONService/bookedTicket";

    //    private static final String baseUrl = "http://localhost:8095/rest/TicketJSONService/removeTicket/100";
//    private static final String baseUrl = "http://localhost:8095/rest/TicketXMLService/getTicket/101";
//
    public static void main(String[] args) {
        String serveseURL = null;
        String typeAnswer = null;
        WebResource webResource = null;
        ClientResponse response;
        try {

            Scanner sc = new Scanner(System.in);
            Client client = Client.create();
            System.out.println(CHOISE_SERVERSE);
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                if (command.equalsIgnoreCase(JSON)) {
                    serveseURL = baseURL + JSON_SERVER;
                    typeAnswer = "application/json";

                } else if (command.equalsIgnoreCase(XML)) {
                    serveseURL = baseURL + XML_SERVER;
                    typeAnswer = "application/xml";
                } else {
                    System.out.println("you entered incorrect atribute");
                   continue;
                }
                System.out.println(CHOISE_COMMAND);
                while (sc.hasNextLine()) {
                    try {
                        String[] arguments = sc.nextLine().replaceAll(" ", "").split(",");
                        if (arguments[0].equalsIgnoreCase(Utils.GET_TICKET)) {
                            webResource = client.resource((serveseURL + GET + arguments[1]));
                            response = webResource.type(typeAnswer).get(ClientResponse.class);
                            Ticket ticket = response.getEntity(Ticket.class);
                            System.out.println(ticket);
                        } else if (arguments[0].equalsIgnoreCase(Utils.REMOVE_TICKET)) {
                            webResource = client.resource((serveseURL + DELETE + arguments[1]));
                            response = webResource.type(typeAnswer).delete(ClientResponse.class);
                            String message = response.getEntity(String.class);
                            System.out.println(message);
                        } else if (arguments[0].equalsIgnoreCase(Utils.PAYED_TICKET)) {
                            webResource = client.resource((serveseURL + PUT));
                            Ticket ticket = new Ticket();
                            ticket.setNumberTicket(Integer.valueOf(arguments[1]));
                            ticket.setCostTicket(Double.valueOf(arguments[2]));
                            response = webResource.type(typeAnswer).put(ClientResponse.class, ticket);
                            String output = response.getEntity(String.class);
                            System.out.println(output);
                        } else if (arguments[0].equalsIgnoreCase(Utils.BOOKED_TICKET)) {

                            webResource = client.resource((serveseURL + POST));
                            Ticket ticket = new Ticket();
                            ticket.setHuman(Utils.getHuman(arguments[1],
                                    arguments[2],
                                    arguments[3],
                                    Utils.getDate(arguments[4])));
                            ticket.setStartCity(arguments[5]);
                            ticket.setFinishCity(arguments[6]);
                            ticket.setArrivaDate(Utils.getDate(arguments[7]));//7
                            ticket.setDepartureDate(Utils.getDate(arguments[8]));//8


                            response = webResource.type(typeAnswer).post(ClientResponse.class, ticket);
                            String output = response.getEntity(String.class);
                            System.out.println(output);
                        }else if(arguments[0].equalsIgnoreCase("change")){
                            System.out.println(CHOISE_SERVERSE);
                            break;

                        }
                        else{
                            System.out.println("you entered incorrect command ");
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("You entered incorrect count args for this command");
                    } catch (ParseException e) {
                        System.out.println("you entered inccorect atribute");
                    }catch (Exception e){
                    System.out.println(e);}
                }
            }


        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    private void demo() {
        com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create();
        client.setFollowRedirects(true); // in case the service redirects

        WebResource resource = client.resource(baseUrl);


        String url = baseUrl + "?id=32";
        resource = client.resource(url);

    }
}

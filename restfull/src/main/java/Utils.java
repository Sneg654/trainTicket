import entity.Human;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public class Utils {

   private static DateFormat format = new SimpleDateFormat("DD.MM.YYYY");
     public final static String GET_TICKET="gt";
     public final static String REMOVE_TICKET="rt";
     public final static String PAYED_TICKET="pt";
     public final static String BOOKED_TICKET="bt";

    public static Date getDate(String date)throws ParseException{
        return format.parse(date);

    }

    public static Human getHuman (String fisrstName ,String lastName,String middleName, Date birthDay){
        Human human=new Human();
        human.setFirstName(fisrstName);
        human.setLastName(lastName);
        human.setMidlleName(middleName);
        human.setBirthday(birthDay);
        return human;
    }
}

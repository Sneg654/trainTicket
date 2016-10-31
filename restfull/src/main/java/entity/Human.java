package entity;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/27/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "lastName",
        "firstName",
        "midlleName",
        "birthday"  })
@XmlRootElement
public class Human {

    private String lastName;

    private String firstName;

    private String midlleName;

    private Date birthday;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidlleName() {
        return midlleName;
    }

    public void setMidlleName(String midlleName) {
        this.midlleName = midlleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Human{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", midlleName='" + midlleName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

import java.util.ArrayList;
import java.util.List;

public class Friends {
    
    private static List<Friends> friendsList = new ArrayList<>();
    private static List<Friends> displayList = new ArrayList<>();
    

    private String firstname;
    private String lastname;
    private int age;
    private String phoneNumber;

     //requires firstname, lastname, and phoneNumber are not null.
     //modifies friendsList, displayList
     //effects Adds a new friend to the friendsList and displayList.

    //Constructor for friend...
    public Friends(String firstname, String lastname, int age, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        friendsList.add(this);
        displayList.add(this);
    }
    //Getters and setters...
    public String getfirstName() {
        return firstname;
    }

    //requires: The first name of the friend.
    //modifies: this.firstname
    //effects: Sets the first name of the friend to the given value.
    public void setfirstName(String firstname) {
        this.firstname = firstname;
    }
    public String getlastName() {
        return lastname;
    }

    //requires: The last name of the friend.
    //modifies: this.lastname
    //effects: Sets the last name of the friend to the given value.
    public void setlastName(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    //requires: The age of the friend.
    //modifies: this.age
    //effects: Sets the age of the friend to the given value.
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //requires: The phone number of the friend.
     //modifies: this.phonenNumber
     //effects: Sets the phone number of the friend to the given value.
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}

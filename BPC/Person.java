package BPC;

public class Person {
    public int id;
    public String fullName;
    public String address;
    public String phone;

    public Person(int id, String fullName, String address, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    @Override
    public String toString() {
        return fullName + " (ID: " + id + ")";
    }
}

package BPC;

import java.util.ArrayList;
import java.util.List;

public class Physiotherapist extends Person {
    public List<String> expertiseAreas;


    public Physiotherapist(int id, String fullName, String address, int phone) {
        super(id, fullName, address, phone);

        this.expertiseAreas = new ArrayList<>();

    }

    public void addExpertise(String expertise) {
        if (!expertiseAreas.contains(expertise)) {
            expertiseAreas.add(expertise);
        }
    }

    @Override
    public String toString() {
        return "Physiotherapist ID: " + id + ", Name: " + fullName + ", Expertise: " + expertiseAreas + ", Address: " + address + ", Phone: "+ phone;
    }

}

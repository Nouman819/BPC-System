package BPC;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    public List<Integer> appointmentIds;

    public Patient(int id, String fullName, String address, int phone) {
        super(id, fullName, address, phone);
        this.appointmentIds = new ArrayList<>();
    }

    public void addAppointmentId(int appointmentId) {
        appointmentIds.add(appointmentId);
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + fullName + ", Address: " + address + ", Phone: " + phone +
                ", Appointments: " + appointmentIds;
    }
}

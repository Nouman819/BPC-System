package BPC;

import java.util.ArrayList;
import java.util.List;

public class Physiotherapist extends Person {
    private final List<String> expertiseAreas;
    private final List<TreatmentSlot> timetable;

    public Physiotherapist(int id, String fullName, String address, int phone) {
        super(id, fullName, address, phone);

        this.expertiseAreas = new ArrayList<>();
        this.timetable = new ArrayList<>();
    }

    public void addExpertise(String expertise) {
        if (!expertiseAreas.contains(expertise)) {
            expertiseAreas.add(expertise);
        }
    }
    public void addTreatmentSlot(TreatmentSlot slot) {
        timetable.add(slot);
    }

    public List<TreatmentSlot> getAvailableSlotsByExpertise(String expertise) {
        List<TreatmentSlot> available = new ArrayList<>();
        if (!expertiseAreas.contains(expertise)) return available;

        for (TreatmentSlot slot : timetable) {
            if (slot.Booked() && slot.getTreatment().getExpertise().equalsIgnoreCase(expertise)) {
                available.add(slot);
            }
        }
        return available;
    }


    public List<TreatmentSlot> getAllAvailableSlots() {
        List<TreatmentSlot> available = new ArrayList<>();
        for (TreatmentSlot slot : timetable) {
            if (slot.Booked()) {
                available.add(slot);
            }
        }
        return available;
    }
    @Override
    public String toString() {
        return "Physiotherapist ID: " + id + ", Name: " + fullName + ", Expertise: " + expertiseAreas + ", Address: " + address + ", Phone: "+ phone;
    }

}

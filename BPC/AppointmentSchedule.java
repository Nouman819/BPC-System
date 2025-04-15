package BPC;

public class AppointmentSchedule {
    private static int nextId = 1;
    private final int id;
    private final Patient patient;
    private final Physiotherapist physiotherapist;
    private final String expertise;  // Expertise as String
    private final TreatmentSlot treatmentSlot;
    private final AppointmentStatus status;

    public AppointmentSchedule(Patient patient, Physiotherapist physiotherapist, String expertise, TreatmentSlot treatmentSlot)
    {
        this.id = nextId++;
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.expertise = expertise;
        this.treatmentSlot = treatmentSlot;
        this.status = AppointmentStatus.BOOKED;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Patient: " + patient.getFullName() +
                ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Expertise: " + expertise + ", Start Time: " + treatmentSlot.getStartTime() + "End Time : " + treatmentSlot.getEndTime() +
                ", Status: " + status;
    }
}

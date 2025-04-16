package BPC;

public class AppointmentSchedule {
    private static int nextId = 1;
    private final int id;
    private final Patient patient;
    private final Physiotherapist physiotherapist;
    private final String expertise;  // Expertise as String
    private final TreatmentSlot treatmentSlot;
    private  AppointmentStatus status;

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
    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
    public AppointmentStatus getStatus() {
        return status;
    }

    public void markAsAttended() {
        this.status = AppointmentStatus.ATTENDED;
    }
    @Override
    public String toString() {
        return "\nAppointment ID: " + id + ", Patient: " + patient.getFullName() +
                ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Expertise: " + expertise + ", Start Time: " + treatmentSlot.getStartTime() + "End Time : " + treatmentSlot.getEndTime() +
                ", Status: " + status;
    }
}

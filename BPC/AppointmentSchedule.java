package BPC;

public class AppointmentSchedule {
    private static int nextId = 1;
    private int id;
    private Patient patient;
    private Physiotherapist physiotherapist;
    private String expertise;  // Expertise as String
    private TreatmentSlot treatmentSlot;
    private AppointmentStatus status;

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

    public Patient getPatient() {

        return patient;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public String getExpertise() {

        return expertise;
    }

    public TreatmentSlot getTreatmentSlot() {
        return treatmentSlot;
    }

    public AppointmentStatus getStatus() {
        return status;
    }


    public void markAsAttended() {
        this.status = AppointmentStatus.ATTENDED;
    }


    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }


    public void reschedule(TreatmentSlot newSlot) {
        this.treatmentSlot = newSlot;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Patient: " + patient.getFullName() +
                ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Expertise: " + expertise + ", Start Time: " + treatmentSlot.getStartTime() + "End Time : " + treatmentSlot.getEndTime() +
                ", Status: " + status;
    }
}

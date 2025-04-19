package BPC;

public class AppointmentSchedule {
    private static int nextId = 1;
    private final int id;
    private final Patient patient;
    private  Physiotherapist physiotherapist;
    private  String expertise;  // Expertise as String
    private  TreatmentSlot treatmentSlot;
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

    public Patient getPatient() {
        return patient;
    }

    public TreatmentSlot getTreatmentSlot() {
        return treatmentSlot;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
    public AppointmentStatus getStatus() {
        return status;
    }
    public void cancel() {
    this.status = AppointmentStatus.CANCELLED;
    }

    public void markAsAttended() {
        this.status = AppointmentStatus.ATTENDED;
    }
    public void reschedule(TreatmentSlot newSlot) {
        if (this.treatmentSlot != null) {
            this.treatmentSlot.cancelSlot();
        }

        newSlot.bookSlot();


        this.treatmentSlot = newSlot;
        this.physiotherapist = newSlot.getPhysiotherapist();
        this.expertise = newSlot.getTreatment().getExpertise();
        this.status = AppointmentStatus.BOOKED;
    }

    @Override
    public String toString() {
        return "\nAppointment ID: " + id + ", Patient: " + patient.getFullName() +
                ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Expertise: " + expertise + ", Start Time: " + treatmentSlot.getStartTime() + "End Time : " + treatmentSlot.getEndTime() +
                ", Status: " + status;
    }
}

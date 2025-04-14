package BPC;

import java.time.LocalDateTime;

public class TreatmentSlot {
    private Treatment treatment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Physiotherapist physiotherapist;
    public boolean isBooked;

    public TreatmentSlot(Treatment treatment, LocalDateTime startTime, LocalDateTime endTime, Physiotherapist physiotherapist) {
        this.treatment = treatment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.physiotherapist = physiotherapist;
    }
    public Treatment getTreatment() {
        return treatment;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void bookSlot() {
        this.isBooked = true;
    }


    public void cancelSlot() {
        this.isBooked = false;
    }
    @Override
    public String toString() {
        return "Treatment: " + treatment.getName() + ", Physiotherapist: " + physiotherapist.getFullName() +
                ", Time: " + startTime + " to " + endTime;
    }
}

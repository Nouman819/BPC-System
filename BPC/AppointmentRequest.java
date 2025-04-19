package BPC;

public class AppointmentRequest {
    private final int patientId;
    private final String expertise;
    private final boolean searchByExpertise;

    public AppointmentRequest(int patientId, String expertiseOrName, boolean searchByExpertise) {
        this.patientId = patientId;
        this.expertise = expertiseOrName;
        this.searchByExpertise = searchByExpertise;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getExpertiseOrName() {
        return expertise;
    }

    public boolean isSearchByExpertise() {
        return searchByExpertise;
    }
}

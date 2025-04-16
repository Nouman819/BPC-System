package BPC;

public class AppointmentRequest {
    private final int patientId;
    private final String expertise;
    private final boolean searchByExpertiseorName;

    public AppointmentRequest(int patientId, String expertiseOrName, boolean searchByExpertiseorName) {
        this.patientId = patientId;
        this.expertise = expertiseOrName;
        this.searchByExpertiseorName = searchByExpertiseorName;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getExpertiseOrName() {
        return expertise;
    }

    public boolean isSearchByExpertiseorName() {
        return searchByExpertiseorName;
    }
}

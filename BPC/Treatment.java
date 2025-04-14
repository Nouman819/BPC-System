package BPC;

public class Treatment {
    public String name;
    public String expertise; // e.g., "Physiotherapy", "Rehabilitation"

    public Treatment(String name, String expertise) {
        this.name = name;
        this.expertise = expertise;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    @Override
    public String toString() {
        return name + " (" + expertise + ")";
    }
}

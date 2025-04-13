package BPC;

public class BPC_Main {
    public static void main(String[] args) {
        Patient patient = new Patient(1, "Nouman","156 Baker St", 12311241);


        System.out.println("Before appointments:");
        System.out.println(patient);

        patient.addAppointmentId(101);

        System.out.println("After adding appointments:");
        System.out.println(patient);
    }
}

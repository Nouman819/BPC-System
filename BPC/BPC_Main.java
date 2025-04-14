package BPC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BPC_Main {
    static int nextPatientId = 1;
    public List<Physiotherapist> physiotherapists = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    /*public void addPhysiotherapist(Physiotherapist physiotherapist) {
        physiotherapists.add(physiotherapist);
    }

    public void printPhysiotherapists() {
        for (Physiotherapist physiotherapist : physiotherapists) {
            System.out.println(physiotherapist);
        }
    }*/

    public boolean isPatientIdExists(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return true;
            }
        }
        return false;
    }


    public void addPatient(Patient patient) {
        patients.add(patient);
    }


    public boolean removePatient(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patients.remove(patient);
                System.out.print("Patient removed from the clinic");
                return true;
            }
            else {
                System.out.print("Patient not found");
                return false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        BPC_Main main = new BPC_Main();

        Scanner scanner = new Scanner(System.in);
        Treatment massage = new Treatment("Massage", "Physiotherapy");
        Treatment acupuncture = new Treatment("Acupuncture", "Rehabilitation");

        ArrayList<String> expertiseList = new ArrayList<>();
        expertiseList.add("Physiotherapy");
        expertiseList.add("Rehabilitation");
        Physiotherapist physio = new Physiotherapist(1, "Dr. Alice", "Walker",12134);

        // TreatmentSlot
        LocalDateTime start = LocalDateTime.of(2025, 4, 15, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 4, 15, 11, 0);
        TreatmentSlot slot = new TreatmentSlot(massage, start, end, physio);


        System.out.println("Before Booking: ");
        System.out.println(slot);
        System.out.println("Is Booked? " + slot.isBooked);


        slot.bookSlot();
        System.out.println("\nAfter Booking: ");
        System.out.println("Is Booked? " + slot.isBooked);


        slot.cancelSlot();
        System.out.println("\nAfter Cancellation: ");
        System.out.println("Is Booked? " + slot.isBooked);

       /* while (true) {
            System.out.println("\n--- Boost Physio Clinic ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Remove Patient");
            System.out.print("Enter your choice: ");
            int subChoice=scanner.nextInt();
            scanner.nextLine();
            switch (subChoice) {
                case 1:
                    while (main.isPatientIdExists(nextPatientId)) {
                        nextPatientId++;
                    }
                    int patientId = nextPatientId++;
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter patient address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter patient phone: ");
                    int phone = scanner.nextInt();
                    main.addPatient(new Patient(patientId, name, address, phone));
                    System.out.print("Patient added with ID: " + patientId + " Name: " + name + " Address: " + address + " Phone: " + phone + "\n");
                    System.out.print("\nKindly Remember your ID for future");
                    break;
                case 2:
                    System.out.print("Enter patient ID to remove: ");
                    patientId = scanner.nextInt();
                    main.removePatient(patientId);
                    scanner.nextLine();
                    break;
            }
        }*/
    }
}


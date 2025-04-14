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

        AppointmentRequest request1 = new AppointmentRequest(101, "Physiotherapy", true);

        System.out.println("Request 1:");
        System.out.println("Patient ID: " + request1.getPatientId());
        System.out.println("Search for Expertise or Name: " + request1.getExpertiseOrName());
        System.out.println("Search By Expertise? " + request1.isSearchByExpertise());


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


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

        Treatment treatment = new Treatment("Back Pain Therapy", "Physiotherapy");


        Physiotherapist physio = new Physiotherapist(1, "Dr, Brown", "alice@example.com", 1213242342);


        Patient patient = new Patient(1, "John","156 Baker", 12341 );


        LocalDateTime start = LocalDateTime.of(2025, 4, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 4, 20, 11, 0);
        TreatmentSlot slot = new TreatmentSlot(treatment, start, end, physio);


        AppointmentSchedule appointment = new AppointmentSchedule(patient, physio, "Physiotherapy", slot);


        System.out.println("Initial Appointment:");
        System.out.println(appointment);

        // Mark as attended
        appointment.markAsAttended();
        System.out.println("\nAfter marking as attended:");
        System.out.println(appointment);

        // Reschedule to a new slot
        LocalDateTime newStart = LocalDateTime.of(2025, 4, 21, 15, 0);
        LocalDateTime newEnd = LocalDateTime.of(2025, 4, 21, 16, 0);
        TreatmentSlot newSlot = new TreatmentSlot(treatment, newStart, newEnd, physio);
        appointment.reschedule(newSlot);
        System.out.println("\nAfter rescheduling:");
        System.out.println(appointment);

        // Cancel the appointment
        appointment.cancel();
        System.out.println("\nAfter cancelling:");
        System.out.println(appointment);


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


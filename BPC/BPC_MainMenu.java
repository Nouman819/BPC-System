package BPC;

import java.time.*;
import java.util.*;
public class BPC_MainMenu {
    static int nextPatientId = 1;
    MainMenuControl mainMenuControl = new MainMenuControl();
    public void sampleData(){
// Physiotherapists
        Physiotherapist helen = new Physiotherapist(1, "Helen", "123 Main St", 5551234);
        helen.addExpertise("Physiotherapy");

        Physiotherapist john = new Physiotherapist(2, "John", "456 Elm St", 5555678);
        john.addExpertise("Rehabilitation");

        Physiotherapist sara = new Physiotherapist(3, "Sara", "789 Maple St", 555-4321);
        sara.addExpertise("Osteopathy");

        mainMenuControl.addPhysiotherapist(helen);
        mainMenuControl.addPhysiotherapist(john);
        mainMenuControl.addPhysiotherapist(sara);

// Treatments
        Treatment massage = new Treatment("Massage", "Physiotherapy");
        Treatment acupuncture = new Treatment("Acupuncture", "Rehabilitation");
        Treatment neural = new Treatment("Neural Mobilisation", "Osteopathy");

// assign sessions per week
        LocalDate baseDate = LocalDate.of(2025, 5, 1);
        int sessionDurationMinutes = 120;

        for (int week = 0; week < 4; week++) {
            for (int i = 0; i < 5; i++) {
                int hour = 9 + (i % 2);
                LocalDate day = baseDate.plusDays(week * 7 + i);
                LocalDateTime start = LocalDateTime.of(day, LocalTime.of(hour, 0));
                LocalDateTime end = start.plusMinutes(sessionDurationMinutes);

                helen.addTreatmentSlot(new TreatmentSlot(massage , start, end, helen));
                john.addTreatmentSlot(new TreatmentSlot(acupuncture, start, end, john));
                sara.addTreatmentSlot(new TreatmentSlot(neural, start, end, sara));
            }
        }

// 15 Patients
        mainMenuControl.addPatient(new Patient(1, "Ali Raza", "Gulshan", 939866487));
        mainMenuControl.addPatient(new Patient(2, "Fatima Noor", "Johar", 958740855));
        mainMenuControl.addPatient(new Patient(3, "Ahmed Khan", "Defense", 440744311));
        mainMenuControl.addPatient(new Patient(4, "Zara Sheikh", "Clifton", 459618679));
        mainMenuControl.addPatient(new Patient(5, "Bilal Aslam", "Nazimabad", 744123567));
        mainMenuControl.addPatient(new Patient(6, "Mariam Siddiqui", "PECHS", 950352247));
        mainMenuControl.addPatient(new Patient(7, "Umer Farooq", "Malir", 969226615));
        mainMenuControl.addPatient(new Patient(108, "Sana Javed", "FB Area", 988100983));
        mainMenuControl.addPatient(new Patient(109, "Hassan Ali", "North Nazimabad", 15509294));
        mainMenuControl.addPatient(new Patient(110, "Ayesha Anwar", "Shah Faisal", 1976110));
        mainMenuControl.addPatient(new Patient(111, "Tariq Mehmood", "Korangi", 119060855));
        mainMenuControl.addPatient(new Patient(112, "Komal Shah", "Gulistan-e-Johar", 971323767));
        mainMenuControl.addPatient(new Patient(113, "Yasir Iqbal", "Gulberg", 1915255));
        mainMenuControl.addPatient(new Patient(114, "Noor Fatima", "Bahadurabad", 3799943));
        mainMenuControl.addPatient(new Patient(115, "Danish Javed", "Landhi", 1006975351));

    }
    public static void main(String[] args) {
        BPC_MainMenu main = new BPC_MainMenu();
        MainMenuControl mainMenuControl = main.mainMenuControl;
        main.sampleData();
        Scanner scanner = new Scanner(System.in);

       while (true) {
           System.out.println("\n**--- Boost Physio Clinic ---**");
           System.out.println("1.   Add/Remove Patient");
           System.out.println("2.   Book Treatment Appointment");
           System.out.println("3.   Attend Appointment");
           System.out.println("4.   Generate Report");
           System.out.println("5.   Exit");
           System.out.print("Enter your choice: ");
            int Choice=scanner.nextInt();
            scanner.nextLine();
            switch (Choice) {
                case 1:
                    System.out.println("1. Add Patient");
                    System.out.println("2. Remove Patient");
                    System.out.println("0. Go Back to Main Menu");
                    int subChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (subChoice == 1) {
                        while (mainMenuControl.isPatientIdExists(nextPatientId)) {
                            nextPatientId++;
                        }
                        int patientId = nextPatientId++;
                        System.out.print("Enter patient name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter patient address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter patient phone: ");
                        int phone = scanner.nextInt();
                        mainMenuControl.addPatient(new Patient(patientId, name, address, phone));
                        System.out.print("Patient added with ID: " + patientId + " Name: " + name + " Address: " + address + " Phone: " + phone + "\n");
                        System.out.print("\nKindly Remember your ID for future");
                        break;
                    } else if (subChoice == 2) {
                        System.out.print("Enter patient ID to remove: ");
                        int patientId = scanner.nextInt();
                        mainMenuControl.removePatient(patientId);
                        scanner.nextLine();
                        break;
                    }
                    else if (subChoice == 0) {
                        break;
                    }
                    else {
                        System.out.println("\nInvalid Choice Entered\n");
                    }
                case 2:
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = mainMenuControl.findPatientById(patientId);

                    if (patient != null) {
                        System.out.println("Patient found:");
                        System.out.println("ID: " + patient.getId() + " Name " + patient.fullName + " Address " + patient.address + " Phone " + patient.phone);

                    } else {
                        System.out.println("No patient found with ID: " + patientId);
                        break;
                    }
                    scanner.nextLine();
                    System.out.println("For Appointment Booking do you want to ...");
                    System.out.print("1. Search by Expertise   ");
                    System.out.println("\n2. Search by Physiotherapist Name   ");
                    System.out.println("0. Go Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();
                    boolean searchByExpertise = (searchChoice == 1);
                    String expertise = null;
                    String physioName = null;
                    if (searchByExpertise) {
                        System.out.print("Enter expertise: ");
                        expertise = scanner.nextLine();
                    }
                    else if (searchChoice == 2) {
                        System.out.print("Enter physiotherapist's name: ");
                        physioName = scanner.nextLine();
                    }
                    else if (searchChoice == 0) {
                        break;
                    }
                    else {
                        System.out.println("\nInvalid Choice Entered\n");
                    }
                    String searchTerm = searchByExpertise ? expertise : physioName;

                    AppointmentRequest request = new AppointmentRequest(patientId, searchTerm, searchByExpertise);
                    mainMenuControl.bookAppointment(request);
                    break;
                case 3:
                    System.out.print("Enter appointment ID to attend: ");
                    int attendId = scanner.nextInt();
                    mainMenuControl.attendAppointment(attendId);
                    break;
                case 4:
                    mainMenuControl.generateReport();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

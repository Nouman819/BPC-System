package BPC;

import java.time.*;
import java.util.*;
public class BPC_MainMenu {
    MainMenuControl mainMenuControl = new MainMenuControl();
    Scanner scanner = new Scanner(System.in);
    public void sampleData(){
// Physiotherapists
        Physiotherapist helen = new Physiotherapist(1, "Dr Helen", "123 Main St", 5551234);
        helen.addExpertise("Physiotherapy");

        Physiotherapist john = new Physiotherapist(2, "Dr John", "456 Elm St", 5555678);
        john.addExpertise("Rehabilitation");

        Physiotherapist sarah = new Physiotherapist(3, "Dr Sarah", "789 Maple St", 555-4321);
        sarah.addExpertise("Osteopathy");

        mainMenuControl.addPhysiotherapist(helen);
        mainMenuControl.addPhysiotherapist(john);
        mainMenuControl.addPhysiotherapist(sarah);

// Treatments
        Treatment massage = new Treatment("Massage", "Physiotherapy");
        Treatment acupuncture = new Treatment("Acupuncture", "Rehabilitation");
        Treatment neural = new Treatment("Neural Mobilisation", "Osteopathy");
        Treatment mobilisation = new Treatment("Mobilisation of spine and joints", "Physiotherapy");
        Treatment pool = new Treatment("Pool Rehabilitation", "Rehabilitation");
// assign sessions per week
        LocalDate baseDate = LocalDate.of(2025, 5, 1);
        int sessionDurationMinutes=120;
        int hour;
        LocalDateTime start;
        LocalDate days;
        LocalDateTime end;
        for (int week = 0; week < 4; week++) {
            for (int i = 0; i < 5; i++) {
                hour = 9 + (i % 2);
                days = baseDate.plusDays(week * 7 + i);
                start = LocalDateTime.of(days, LocalTime.of(hour, 0));
                end = start.plusMinutes(sessionDurationMinutes);

                helen.addTreatmentSlot(new TreatmentSlot(massage , start, end, helen));
                sarah.addTreatmentSlot(new TreatmentSlot(neural, start, end, sarah));
                john.addTreatmentSlot(new TreatmentSlot(pool, start, end, john));

            }
        }
        int sessionMinutes=60;
        for (int week = 0; week < 4; week++) {
            for (int i = 0; i < 5; i++) {
                 hour = 12 + (i % 2);
                 days = baseDate.plusDays(week * 7 + i);
                 start = LocalDateTime.of(days, LocalTime.of(hour, 0));
                 end = start.plusMinutes(sessionMinutes);
                john.addTreatmentSlot(new TreatmentSlot(acupuncture, start, end, john));
                helen.addTreatmentSlot(new TreatmentSlot(mobilisation, start, end, helen));
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
    public void MainMenu() {
        while (true) {
            System.out.println("\n**--- Boost Physio Clinic ---**");
            System.out.println("1.   Add/Remove Patient");
            System.out.println("2.   Book Treatment Appointment");
            System.out.println("3.   Change or Cancel Appointment");
            System.out.println("4.   Attend Appointment");
            System.out.println("5.   Generate Report");
            System.out.println("6.   Exit");
            System.out.print("Enter your choice: ");
            int Choice;
            try {
                Choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (Choice) {
                case 1:
                    while (true) {
                        System.out.println("1. Add Patient");
                        System.out.println("2. Remove Patient");
                        System.out.println("0. Go Back to Main Menu");
                        int subChoice;
                        try {
                            subChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter 1, 2, or 0.");
                            continue;
                        }
                        int patientId;
                        if (subChoice == 1) {
                            patientId = mainMenuControl.getAvailablePatientId();
                            System.out.print("Enter patient name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter patient address: ");
                            String address = scanner.nextLine();
                            System.out.print("Enter patient phone: ");
                            int phone = scanner.nextInt();
                            scanner.nextLine();
                            mainMenuControl.addPatient(new Patient(patientId, name, address, phone));
                            System.out.print("Patient added with ID: " + patientId + " Name: " + name + " Address: " + address + " Phone: " + phone + "\n");
                            System.out.print("\nKindly Remember your ID for future\n");
                        } else if (subChoice == 2) {
                            System.out.print("Enter patient ID to remove: ");
                            patientId = scanner.nextInt();
                            scanner.nextLine();
                            mainMenuControl.removePatient(patientId);
                        } else if (subChoice == 0) {
                            break;
                        } else {
                            System.out.println("\nInvalid Choice Entered\n");
                        }
                    }
                    break;

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
                    while(true) {
                        System.out.println("For Appointment Booking do you want to ...");
                        System.out.print("1. Search by Expertise   ");
                        System.out.println("\n2. Search by Physiotherapist Name   ");
                        System.out.println("0. Go Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int searchChoice;
                        try {
                            searchChoice = Integer.parseInt(scanner.nextLine());

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number 1,2 and 0.");
                            continue;
                        }

                        boolean searchByExpertise = (searchChoice == 1);
                        String expertise = null;
                        String physioName = null;
                        if (searchByExpertise) {
                            System.out.println("Available Expertises Area are: \n•. Physiotherapy\n•. Rehabilitation\n•. Osteopathy\n ");
                            System.out.print("Enter your choice from available expertise areas: ");
                            expertise = scanner.nextLine();


                        } else if (searchChoice == 2) {
                            System.out.println("Available Physiotherapists are: \n 1. Dr Helen (Expertise Area Physiotherapy)\n 2. Dr John (Expertise Area Rehabilitation)\n 3. Dr Sarah (Expertise Area Osteopathy)\n ");
                            System.out.print("Enter your choice from available physiotherapist's name: ");
                            physioName = scanner.nextLine();

                        } else if (searchChoice == 0) {
                            break;
                        } else {
                            System.out.println("\nInvalid Choice Entered\n");
                        }
                        String searchTerm = searchByExpertise ? expertise : physioName;

                        AppointmentRequest request = new AppointmentRequest(patientId, searchTerm, searchByExpertise);
                        mainMenuControl.bookAppointment(request);
                        break;
                    }
                    break;
                case 3:
                    System.out.print("Enter appointment ID to cancel or change: ");
                    int appointmentId = scanner.nextInt();
                    scanner.nextLine();
                    while (true) {
                        System.out.println("1. Cancel Appointment");
                        System.out.println("2. Change Appointment");
                        System.out.println("0. Go Back");
                        System.out.print("Enter choice: ");

                        int actionChoice;
                        try {
                            actionChoice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter 1, 2, or 0.");
                            continue;
                        }

                        if (actionChoice == 1) {
                            mainMenuControl.cancelAppointment(appointmentId);
                            break;
                        } else if (actionChoice == 2) {
                            mainMenuControl.rescheduleAppointment(appointmentId);
                            break;
                        } else if (actionChoice == 0) {
                            break;
                        } else {
                            System.out.println("\nInvalid choice entered. Please enter 1, 2, or 0.\n");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter appointment ID to attend: ");
                    int attendId = scanner.nextInt();
                    mainMenuControl.attendAppointment(attendId);
                    scanner.nextLine();
                    break;


                case 5:
                    mainMenuControl.generateReport();
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    public static void main(String[] args) {
        BPC_MainMenu main = new BPC_MainMenu();
        main.sampleData();
        main.MainMenu();


    }
}

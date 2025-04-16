package BPC;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BPC_Main {
    static int nextPatientId = 1;
    private final List<Patient> patients;
    private final List<Physiotherapist> physiotherapists;
    private final List<AppointmentSchedule> appointments;

    public BPC_Main() {
        this.patients = new ArrayList<>();
        this.physiotherapists = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

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


    public void removePatient(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patients.remove(patient);
                System.out.print("Patient removed from the clinic");
            }
            else {
                System.out.print("Patient not found");
            }
            return;
        }
    }
    public Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public void addPhysiotherapist(Physiotherapist physiotherapist) {
        physiotherapists.add(physiotherapist);
    }


    public Physiotherapist findPhysiotherapistByName(String name) {
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getFullName().equalsIgnoreCase(name)) {
                return physio;
            }
        }
        return null;
    }

    public void bookAppointment(AppointmentRequest request) {
        Patient patient = findPatientById(request.getPatientId());
        List<TreatmentSlot> availableSlots = new ArrayList<>();
        if (request.isSearchByExpertise()) {

            for (Physiotherapist physio : physiotherapists) {
                availableSlots.addAll(physio.getAvailableSlotsByExpertise(request.getExpertiseOrName()));
            }
        } else {

            Physiotherapist physio = findPhysiotherapistByName(request.getExpertiseOrName());
            if (physio != null) {
                availableSlots.addAll(physio.getAllAvailableSlots());
            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("No available appointments found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Available appointments:");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }
        System.out.print("Choose an appointment: ");
        int choice = scanner.nextInt();
        TreatmentSlot selectedSlot = availableSlots.get(choice - 1);

        AppointmentSchedule appointment = new AppointmentSchedule(patient, selectedSlot.getPhysiotherapist(), selectedSlot.getTreatment().getExpertise(), selectedSlot);
        appointments.add(appointment);
        patient.addAppointmentId(appointment.getId());
        System.out.println("Appointment booked successfully: " + appointment);

    }
    public void attendAppointment(int appointmentId) {
        for (AppointmentSchedule appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                appointment.markAsAttended();
                System.out.println("Appointment marked as attended.");
                return;
            }
        }
        System.out.println("Appointment not found!");
    }

    public void sampleData(){
// Physiotherapists
        Physiotherapist helen = new Physiotherapist(1, "Helen", "123 Main St", 5551234);
        helen.addExpertise("Physiotherapy");
        helen.addExpertise("Massage" + "Mobilisation of spine and joints ");

        Physiotherapist john = new Physiotherapist(2, "John", "456 Elm St", 5555678);
        john.addExpertise("Rehabilitation");
        john.addExpertise("Acupuncture");

        Physiotherapist sara = new Physiotherapist(3, "Sara", "789 Maple St", 555-4321);
        sara.addExpertise("Osteopathy");
        sara.addExpertise("Pool Rehabilitation");

        addPhysiotherapist(helen);
        addPhysiotherapist(john);
        addPhysiotherapist(sara);

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
        addPatient(new Patient(1, "Ali Raza", "Gulshan", 939866487));
        addPatient(new Patient(2, "Fatima Noor", "Johar", 958740855));
        addPatient(new Patient(3, "Ahmed Khan", "Defense", 440744311));
        addPatient(new Patient(4, "Zara Sheikh", "Clifton", 459618679));
        addPatient(new Patient(5, "Bilal Aslam", "Nazimabad", 744123567));
        addPatient(new Patient(6, "Mariam Siddiqui", "PECHS", 950352247));
        addPatient(new Patient(7, "Umer Farooq", "Malir", 969226615));
        addPatient(new Patient(108, "Sana Javed", "FB Area", 988100983));
        addPatient(new Patient(109, "Hassan Ali", "North Nazimabad", 15509294));
        addPatient(new Patient(110, "Ayesha Anwar", "Shah Faisal", 1976110));
        addPatient(new Patient(111, "Tariq Mehmood", "Korangi", 119060855));
        addPatient(new Patient(112, "Komal Shah", "Gulistan-e-Johar", 971323767));
        addPatient(new Patient(113, "Yasir Iqbal", "Gulberg", 1915255));
        addPatient(new Patient(114, "Noor Fatima", "Bahadurabad", 3799943));
        addPatient(new Patient(115, "Danish Javed", "Landhi", 1006975351));

    }
    public static void main(String[] args) {
        BPC_Main main = new BPC_Main();
        main.sampleData();
        Scanner scanner = new Scanner(System.in);


       while (true) {
           System.out.println("\n--- Boost Physio Clinic ---");
           System.out.println("1. Add/Remove Patient");
           System.out.println("2. Book Treatment Appointment");
           System.out.println("3. Attend Appointment");
           System.out.println("4. Exit");
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
                    } else if (subChoice == 2) {
                        System.out.print("Enter patient ID to remove: ");
                        int patientId = scanner.nextInt();
                        main.removePatient(patientId);
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
                    Patient patient = main.findPatientById(patientId);

                    if (patient != null) {
                        System.out.println("Patient found:");
                        System.out.println("ID: " + patient.getId() + " Name " + patient.fullName + " Address " + patient.address + " Phone " + patient.phone);

                    } else {
                        System.out.println("No patient found with ID: " + patientId);
                        break;
                    }

                    scanner.nextLine();
                    System.out.print("Search by Expertise (1) or Physiotherapist Name (2): ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();
                    boolean searchByExpertise = (searchChoice == 1);
                    System.out.print("Enter expertise or physiotherapist's name: ");
                    String expertiseOrName = scanner.nextLine();
                    AppointmentRequest request = new AppointmentRequest(patientId, expertiseOrName, searchByExpertise);
                    main.bookAppointment(request);
                    break;
                case 3:
                    System.out.print("Enter appointment ID to attend: ");
                    int attendId = scanner.nextInt();
                    main.attendAppointment(attendId);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


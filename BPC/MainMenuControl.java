package BPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenuControl {

    private final List<Patient> patients;
    private final List<Physiotherapist> physiotherapists;
    private final List<AppointmentSchedule> appointments;

    public MainMenuControl() {
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
        // This loop is to restrict the patient to book only one appointment at the time
     /* for (AppointmentSchedule a : appointments) {
            if (a.getPatient().getId() == patient.getId() && a.getStatus() == AppointmentStatus.BOOKED) {
                System.out.println("This patient already has a booked appointment.");
                return;
            }
        }*/

        List<TreatmentSlot> availableSlots = new ArrayList<>();

        if (request.isSearchByExpertiseorName()) {
            for (Physiotherapist physio : physiotherapists) {
                availableSlots.addAll(physio.getAvailableSlotsByExpertise(request.getExpertiseOrName()));
            }
        } else {
            Physiotherapist physio = findPhysiotherapistByName(request.getExpertiseOrName());
            if (physio != null) {
                availableSlots.addAll(physio.getAllAvailableSlots());
            } else {
                System.out.println("Physiotherapist not found.");
                return;
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
        scanner.nextLine();

        if (choice < 1 || choice > availableSlots.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        TreatmentSlot selectedSlot = availableSlots.get(choice - 1);
        selectedSlot.bookSlot();

        AppointmentSchedule appointment = new AppointmentSchedule(
                patient,
                selectedSlot.getPhysiotherapist(),
                selectedSlot.getTreatment().getExpertise(),
                selectedSlot
        );

        appointments.add(appointment);
        patient.addAppointmentId(appointment.getId());

        System.out.println("Appointment booked successfully:");
        System.out.println(appointment);
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
    public void generateReport() {
        System.out.println("\n**--- Treatment Appointments Report ---**");
        for (Physiotherapist physiotherapist : physiotherapists) {
            System.out.println("\nPhysiotherapist: " + physiotherapist.getFullName());
            for (AppointmentSchedule appointment : appointments) {
                if (appointment.getPhysiotherapist() == physiotherapist) {
                    System.out.println(appointment);
                }
            }
        }


        System.out.println("\n***--** Physiotherapists by Attended Appointments ***--**");
        physiotherapists.sort((a, b) -> {
            int countA = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == a && app.getStatus() == AppointmentStatus.ATTENDED).count();
            int countB = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == b && app.getStatus() == AppointmentStatus.ATTENDED).count();
            return Integer.compare(countB, countA); // descending order
        });

        for (Physiotherapist physiotherapist : physiotherapists) {
            int attendedCount = (int) appointments.stream().filter(app -> app.getPhysiotherapist() == physiotherapist && app.getStatus() == AppointmentStatus.ATTENDED).count();
            System.out.println(physiotherapist.getFullName() + " - Attended: " + attendedCount);
        }
    }
}

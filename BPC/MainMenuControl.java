package BPC;

import java.util.*;

public class MainMenuControl {

    private final List<Patient> patients;
    private final List<Physiotherapist> physiotherapists;
    private final List<AppointmentSchedule> appointments;
    Scanner scanner = new Scanner(System.in);
    public MainMenuControl() {
        this.patients = new ArrayList<>();
        this.physiotherapists = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    public int getAvailablePatientId() {
        Set<Integer> usedIds = new HashSet<>();
        for (Patient patient : patients) {
            usedIds.add(patient.getId());
        }
        int id = 1;
        while (usedIds.contains(id)) {
            id++;
        }
        return id;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(int id) {
        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (patient.getId() == id) {
                iterator.remove();
                System.out.println("Patient with ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Patient not found with ID " + id);
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
        for (AppointmentSchedule a : appointments) {
            if (a.getPatient().getId() == patient.getId() && a.getStatus() == AppointmentStatus.BOOKED) {
                System.out.println("This patient already has a booked appointment. If you want a book appointment, please cancel or attend previous one. Thanks");
                return;
            }
        }

        List<TreatmentSlot> availableSlots = new ArrayList<>();

        if (request.isSearchByExpertise()) {
            for (Physiotherapist physio : physiotherapists) {
                availableSlots.addAll(physio.getAvailableSlotsByExpertise(request.getExpertiseOrName()));
            }
        } else  {
            Physiotherapist physio = findPhysiotherapistByName(request.getExpertiseOrName());
            if (physio != null) {
                availableSlots.addAll(physio.getAllAvailableSlots());
            } else {
                System.out.println("Physiotherapist not found. Enter Name from Available list");
                return;
            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("Something wrong or No available appointments found.");
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
    public AppointmentSchedule findAppointmentById(int appointmentId) {
        for (AppointmentSchedule appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null; // return null if not found
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
    public void cancelAppointment(int appointmentId) {
        for (AppointmentSchedule appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
                    System.out.println("This appointment has already been cancelled.");
                    return;
                }

                TreatmentSlot slot = appointment.getTreatmentSlot();
                if (slot != null) {
                    slot.cancelSlot();
                }

                appointment.cancel();
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }

        System.out.println("Appointment not found!");
    }

    public void rescheduleAppointment(int appointmentId) {
        AppointmentSchedule appointment = findAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("Choose how you want to search new slots:");
        System.out.println("1. By Expertise");
        System.out.println("2. By Physiotherapist Name");
        int searChoice = scanner.nextInt();
        scanner.nextLine();

        List<TreatmentSlot> availableSlots = new ArrayList<>();

        if (searChoice == 1) {
            System.out.print("Enter expertise: ");
            String expertise = scanner.nextLine();

            for (Physiotherapist physio : getPhysiotherapists()) {
                for (TreatmentSlot slot : physio.getTimetable()) {
                    if (!slot.isBooked() &&
                            slot.getTreatment().getExpertise().equalsIgnoreCase(expertise) &&
                            slot != appointment.getTreatmentSlot()) {
                        availableSlots.add(slot);
                    }
                }
            }

        } else if (searChoice == 2) {
            System.out.print("Enter physiotherapist's name: ");
           String physioName = scanner.nextLine();

            Physiotherapist physio = findPhysiotherapistByName(physioName);
            if (physio != null) {
                for (TreatmentSlot slot : physio.getTimetable()) {
                    if (!slot.isBooked() && slot != appointment.getTreatmentSlot()) {
                        availableSlots.add(slot);
                    }
                }
            } else {
                System.out.println("Physiotherapist not found.");

            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("No available slots found for rescheduling.");

        }

        System.out.println("Available Slots for Rescheduling:");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }

        System.out.print("Select a new slot number: ");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();

        if (selectedIndex < 1 || selectedIndex > availableSlots.size()) {
            System.out.println("Invalid selection.");
        }



        TreatmentSlot oldSlot = appointment.getTreatmentSlot();
        TreatmentSlot newSlot = availableSlots.get(selectedIndex - 1);

        oldSlot.cancelSlot();
        newSlot.bookSlot();

        appointment.reschedule(newSlot);
        System.out.println("Appointment rescheduled successfully.");
        System.out.println("Updated Appointment Details: " + appointment);

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

package JTest;

import BPC.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuControlTest {
    MainMenuControl control;
    Patient patient;
    Physiotherapist physiotherapist;
    TreatmentSlot treatmentSlot;
    Treatment treatment;
    LocalDateTime start;
    LocalDateTime end;
    @BeforeEach
    void setUp() {
        control = new MainMenuControl();
        patient  = new Patient(1, "Ali", "Karachi", 123);
        control.addPatient(patient);

        physiotherapist = new Physiotherapist(1, "Dr. Helen", "Main St", 321);
        control.addPhysiotherapist(physiotherapist);

        treatment = new Treatment("Massage", "Physiotherapy");
        start = LocalDateTime.of(2025, 4, 21, 10, 0);
        end = start.plusHours(1);
        treatmentSlot = new TreatmentSlot(treatment, start, end, physiotherapist);
    }

    @Test
    void addPatient() {
        assertEquals(patient, control.findPatientById(1));
    }

    @Test
    void removePatient() {
        control.removePatient(1);
        assertNull(control.findPatientById(1));
    }

    @Test
    void bookAppointment() {

        AppointmentRequest request = new AppointmentRequest(1, "Dr. Helen", true);
        String input = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.bookAppointment(request);

        System.setOut(System.out);
        System.setIn(System.in);

        assertTrue(out.toString().contains("Appointment booked successfully:"), out.toString());
    }

    @Test
    void attendAppointment() {
        physiotherapist.addTreatmentSlot(treatmentSlot);

        AppointmentSchedule appointmentSchedule = new AppointmentSchedule(patient, physiotherapist, "Physiotherapy", treatmentSlot);
        control.getAppointments().add(appointmentSchedule);

        control.attendAppointment(appointmentSchedule.getId());
        assertEquals(AppointmentStatus.ATTENDED, appointmentSchedule.getStatus());
    }

    @Test
    void cancelAppointment() {
        physiotherapist.addTreatmentSlot(treatmentSlot);

        AppointmentSchedule appointmentSchedule = new AppointmentSchedule(patient, physiotherapist, "Physiotherapy", treatmentSlot);
        control.getAppointments().add(appointmentSchedule);

        control.cancelAppointment(appointmentSchedule.getId());
        assertEquals(AppointmentStatus.CANCELLED, appointmentSchedule.getStatus());
    }

    @Test
    void generateReport() {

        Physiotherapist helen = new Physiotherapist(1, "Dr. Helen", "Main St", 321);
        Physiotherapist john = new Physiotherapist(2, "Dr. John", "Elm St", 321);
        control.addPhysiotherapist(helen);
        control.addPhysiotherapist(john);

        Treatment treatment = new Treatment("Massage", "Physiotherapy");
        LocalDateTime start1 = LocalDateTime.of(2025, 4, 21, 10, 0);
        LocalDateTime end1 = start1.plusHours(1);

        LocalDateTime start2 = LocalDateTime.of(2025, 4, 22, 11, 0);
        LocalDateTime end2 = start2.plusHours(1);

        TreatmentSlot slot1 = new TreatmentSlot(treatment, start1, end1, helen);
        TreatmentSlot slot2 = new TreatmentSlot(treatment, start2, end2, john);
        helen.addTreatmentSlot(slot1);
        john.addTreatmentSlot(slot2);

        AppointmentSchedule appointmentSchedule1 = new AppointmentSchedule(patient, helen, "Physiotherapy", slot1);
        AppointmentSchedule appointmentSchedule2 = new AppointmentSchedule(patient, john, "Physiotherapy", slot2);
        appointmentSchedule1.setStatus(AppointmentStatus.ATTENDED);
        appointmentSchedule2.setStatus(AppointmentStatus.BOOKED);

        control.getAppointments().add(appointmentSchedule1);
        control.getAppointments().add(appointmentSchedule2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        control.generateReport();

        String output = out.toString();

        assertTrue(output.contains("Dr. Helen"));
        assertTrue(output.contains("Attended: 1"));
        assertTrue(output.contains("Dr. John"));
        assertTrue(output.contains("Attended: 0"));

        System.setOut(System.out);
    }
}

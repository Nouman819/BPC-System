package BPC;

import java.util.ArrayList;
import java.util.List;

public class BPC_Main {
    public List<Physiotherapist> physiotherapists=new ArrayList<>();

    public void addPhysiotherapist(Physiotherapist physiotherapist) {
        physiotherapists.add(physiotherapist);
    }
    public void printPhysiotherapists() {
        for (Physiotherapist physiotherapist : physiotherapists) {
            System.out.println(physiotherapist);
        }
    }

    public static void main(String[] args) {
        BPC_Main main = new BPC_Main();
        Patient patient = new Patient(1, "Nouman","156 Baker St", 12311241);


        System.out.println("Before appointments:");
        System.out.println(patient);

        patient.addAppointmentId(101);

        System.out.println("After adding appointments:");
        System.out.println(patient);
// Physiotherapists


        Physiotherapist helen = new Physiotherapist(1, "Helen", "123 Main St", 5551234);
        helen.addExpertise("Physiotherapy");
        helen.addExpertise("Massage");

        Physiotherapist john = new Physiotherapist(2, "John", "456 Elm St", 555567);
        john.addExpertise("Rehabilitation");
        john.addExpertise("Acupuncture");

        Physiotherapist sara = new Physiotherapist(3, "Sara", "789 Maple St", 5554321);
        sara.addExpertise("Osteopathy");
        sara.addExpertise("Pool Rehabilitation");

        main.addPhysiotherapist(helen);
        main.addPhysiotherapist(john);
        main.addPhysiotherapist(sara);

        System.out.println("Physiotherapists in Clinic:");
        main.printPhysiotherapists();

    }
}

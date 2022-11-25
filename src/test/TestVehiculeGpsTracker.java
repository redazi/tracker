package test;

import entities.VehiculeGpsTrqcker;
import java.util.Date;
import service.TrackerService;
import service.VehiculeGpsTrackerService;
import service.vehiculeService;

public class TestVehiculeGpsTracker {
    public static void main(String[] args) {
        VehiculeGpsTrackerService vgts = new VehiculeGpsTrackerService();
        vehiculeService vs= new vehiculeService();
        TrackerService ts = new TrackerService();


        //creation de l'objet vehiculegpstracker, il faut entrer des id deja existants :


       vgts.create(new VehiculeGpsTrqcker(vs.findById(1).getId(),ts.findById(1).getId(),new Date("2021/02/07"),new Date("2021/06/17")));
       vgts.create(new VehiculeGpsTrqcker(vs.findById(3).getId(),ts.findById(2).getId(),new Date("2021/02/18"),new Date("2021/02/25")));
       vgts.create(new VehiculeGpsTrqcker(vs.findById(4).getId(),ts.findById(1).getId(),new Date("2021/02/26"),new Date("2021/02/30")));

        //suppression du vehicule en cascade
        //vs.delete(vs.findById(1));

        //suppression du tracker en cascade
         //ts.delete(ts.findById(2));
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        for(VehiculeGpsTrqcker t : vgts.findAll()) {
            System.out.println(t);
        }
}

}

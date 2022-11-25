package test;

import entities.User;
import entities.Vehicule;
import java.security.MessageDigest;
import service.UserService;
import service.vehiculeService;

public class testVehicule {

 //fonction de hashage
    
   static public String passe(String password1)throws Exception
    {
     String password =password1 ;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

      /*  //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("En format hexa : " + sb.toString());*/

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     System.out.println("En format hexa : " + hexString.toString());
     return hexString.toString();
    }
    public static void main(String[] args) throws Exception {

       // vehiculeService vs= new vehiculeService();
       UserService us = new UserService();
  
           
         us.create(new User("redaaa",passe("1234")));
   
     /*   vs.create(new Vehicule("ha1dezz","audi","sport"));
        vs.create(new Vehicule("AZ78551d","audi","sport"));
        vs.create(new Vehicule("azez122","mercedes","sidan"));*/
         //supression du vehicule avec un id deja existant :
              // vs.delete(vs.findById(1));

        for(User v : us.findAll()) {
            System.out.println(v);
        }
    }
}



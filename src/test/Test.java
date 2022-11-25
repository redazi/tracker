package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

public class Test {
	
	public static void main(String[] args) {
		try {
                    /*
			String TTracker = "create table tracker ("
					+ "id int primary key auto_increment,"
					+ "simNumber varchar(20));";
			String TPosition = "create table position ("
					+ "id int primary key auto_increment,"
					+ "latitude double,"
					+ "longitude double,"
					+ "date date, "
					+ "idTracker int);";
			/*String fk = "alter table position add "
					+ "constraint fk foreign key (idTracker) "
					+ "references tracker(id);";
                        String vehicule = "create table vehicule ("
					+ "id int primary key auto_increment,"
					+ "matricule varchar(20),"
					+ "marque varchar(20),"
					+ "type varchar(20))";
                        */
                     String auth = "create table auth ("
					+ "id int primary key auto_increment,"
					+ "user varchar(20),"
					+ "password varchar(20))";
					
                        /*
                        	String vehiculegts = "create table vehiculegpstracker ("
                                       + "id int unique auto_increment,"
					+ "id_vehicule int,"
					+ "dated date, "
					+ "datef date, "
					+ "id_tracker int,"
			+ "CONSTRAINT PK_vt PRIMARY KEY (id_vehicule,id_tracker))";

			String fk1 = "alter table position add "
					+ "constraint fk foreign key (idTracker) "
					+ "references tracker(id)";
			String fk2 = "alter table vehiculegpstracker add "
					+ "constraint fk1 foreign key (id_tracker) "
					+ "references tracker(id)";
			String fk3 = "alter table vehiculegpstracker add "
					+ "constraint fk2 foreign key (id_vehicule) "
					+ "references vehicule(id)";
*/



			Statement st = Connexion.getConnection().createStatement();


			st.execute("SET FOREIGN_KEY_CHECKS = 0");
			//st.execute("drop table if exists tracker");
			//st.execute("drop table if exists position");
			//st.execute("drop table if exists vehicule");
			//st.execute("drop table if exists vehiculegpstracker");
			st.execute("SET FOREIGN_KEY_CHECKS = 1");
			//st.executeUpdate(TTracker);
			//st.executeUpdate(TPosition);
			//st.executeUpdate(vehicule);
			//st.executeUpdate(fk1);
			st.executeUpdate(auth);
			//st.executeUpdate(fk2);
			//st.executeUpdate(fk3);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

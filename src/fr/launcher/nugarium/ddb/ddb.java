package fr.launcher.nugarium.ddb;

import com.sun.jmx.snmp.agent.SnmpUserDataFactory;

import java.sql.*;
import java.util.ArrayList;

public class ddb {

    public static String statue;
    public static String raison;
    public static ArrayList whitelistuser = new ArrayList();
    public static boolean q = false;

    public ddb() {
    }

    public static void ddbinit(){
        System.out.println("Chargement de la base de donné ...");

        try {
            // Charger le driver mysql
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver charger avec succès");

            //crée la connection
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://138.201.221.81:25642/launcher", "admin", "shghsjSJGJ859");
            System.out.println("Connection a la base de donné réussi");
            q = true;


            //état de connection
            Statement st  = cnx.createStatement();

            //créer une requet de selection
            ResultSet resmaint = st.executeQuery("select * from maintenance");
            ResultSet reswl = st.executeQuery("select * from whitelistuser");



            //parcours des données
            while(resmaint.next()){
                statue = resmaint.getString(1);
                raison = resmaint.getString(2);
                System.out.println("Statue : " + statue + " Raison : " + raison);
            }

            while(reswl.next()){
                whitelistuser.add(reswl.getString("pseudo"));
                System.out.println(reswl.getString("pseudo"));

            }



        }catch (Exception e){
            System.out.println("Une erreur est survenue");
            e.printStackTrace();
        }


    }

}

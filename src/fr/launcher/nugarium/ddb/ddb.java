/*package fr.launcher.nugarium.ddb;

import com.sun.jmx.snmp.agent.SnmpUserDataFactory;

import java.sql.*;

public class ddb {

    public static String statue;
    public static String raison;
    public static boolean q = false;

    public ddb() {
    }

    public static void ddbinit(){
        System.out.println("Chargement de la base de donné ...");

        try {
            // Charger le driver mysql
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver charger avec succès");

            //crée la connection
            Connection cnx = DriverManager.getConnection("", "", "");
            System.out.println("Connection a la base de donné réussi");
            q = true;


            //état de connection
            Statement st  = cnx.createStatement();

            //créer une requet de selection
            ResultSet res = st.executeQuery("select * from test");



            //parcours des données
            while(res.next()){
                statue = res.getString(1);
                raison = res.getString(2);
                System.out.println("Statue : " + statue + " Raison : " + raison);


            }



        }catch (Exception e){
            System.out.println("Une erreur est survenue");
            e.printStackTrace();
        }


    }

}*/

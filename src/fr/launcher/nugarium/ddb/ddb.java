package fr.launcher.nugarium.ddb;


import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.RSAKeyValue;
import fr.launcher.nugarium.HomePanel;
import fr.launcher.nugarium.LauncherPanel;
import fr.launcher.nugarium.launcher;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ddb {

    public static JsonDDB json;
    public static String perm;
    public static String raison;
    public static String statue;
    public static boolean q = false;
    public static ArrayList<String> wluser = new ArrayList<String>();


    public static Connection connection;


    public ddb() {
        try {
            json = new JsonDDB();
            json.JsonReader();
            setupConnexion();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setupConnexion() {
        try {

            //Charger le driver mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            //crée la connection
            String url = "jdbc:mysql://" + json.data.getHost() + ":3306" + '/' + json.data.getDdbname();
            connection = DriverManager.getConnection(url, json.data.getUsername(), json.data.getPassword());
            System.out.println("Connecté à la base de données !");
            q = true;

            //Crée un état de connection
            Statement st = connection.createStatement();

            //Crée une requete

            //Si le token n'est pas null sa

            ResultSet res_maintenance = st.executeQuery("SELECT * FROM maintenance");

            while (res_maintenance.next()) {
                raison = res_maintenance.getString("raison");
                statue = res_maintenance.getString("statue");
            }

            ResultSet res_wluser = st.executeQuery("SELECT * FROM whitelistuser");

            while (res_wluser.next()) {
                wluser.add(res_wluser.getString(1));
            }



            if (LauncherPanel.saver.get("token") != null) {

                ResultSet res = st.executeQuery("SELECT parent FROM `permissions_inheritance` WHERE child LIKE '%" + LauncherPanel.saver.get("id") + "%'");


                while (res.next()) {
                    perm = res.getString("parent") + ".png";
                }

                //Sinon prendre l'id de quand on se connecte
            } else {
                ResultSet res = st.executeQuery("SELECT parent FROM `permissions_inheritance` WHERE child LIKE '%" + launcher.ID + "%'");
                while (res.next()) {
                    perm = res.getString("parent") + ".png";
                }
            }

            if (perm == null)
            {
                perm = "Spationaute" + ".png";
            }
            else if (perm.contains("default")){
                perm = "Spationaute" + ".png";
            }

            System.out.println(perm);

        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("Impossible de se connecter à MYSQL");
            System.out.println(e.getMessage());
        }

    }


}
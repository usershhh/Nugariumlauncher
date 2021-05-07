package fr.launcher.nugarium.ddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Data {

    public String maintenance;
    public String maintenance_raison;
    public String host;
    public String username;
    public String password;
    public String ddbname;

    public String getDdbname() {
        return ddbname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getMaintenance(){
        return maintenance;
    }
    public String getMaintenanceRaison(){
        return maintenance_raison;
    }

    public String getHost()
    {
        return host;
    }

}

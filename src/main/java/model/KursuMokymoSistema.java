package model;

import java.io.Serializable;
import java.util.ArrayList;

public class KursuMokymoSistema implements Serializable {

    private String name;
    private String version;
    private ArrayList<Vartotojas> allSystemUsers;
    private ArrayList<Kursas> allSystemCourses;

    public KursuMokymoSistema(String name, String version, ArrayList<Vartotojas> allSystemUsers, ArrayList<Kursas> allSystemCourses) {
        this.name = name;
        this.version = version;
        this.allSystemUsers = allSystemUsers;
        this.allSystemCourses = allSystemCourses;
    }

    public KursuMokymoSistema() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<Vartotojas> getAllSystemUsers() {
        return allSystemUsers;
    }

    public void setAllSystemUsers(ArrayList<Vartotojas> allSystemUsers) {
        this.allSystemUsers = allSystemUsers;
    }

    public ArrayList<Kursas> getAllSystemCourses() {
        return allSystemCourses;
    }

    public void setAllSystemCourses(ArrayList<Kursas> allSystemCourses) {
        this.allSystemCourses = allSystemCourses;
    }
}

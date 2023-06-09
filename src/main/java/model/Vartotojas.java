package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "vartotojas")
public abstract class Vartotojas implements Siuntimas, Serializable {

    private String login;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateCreated;
    private LocalDate lastModified;
    private boolean isActive;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Kursas> myCourses;
    @OneToMany(mappedBy = "creator" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Kursas> cataloguesCreated;


    public Vartotojas(String login, String password, int id, LocalDate dateCreated, LocalDate lastModified, boolean isActive) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.isActive = isActive;
    }

    public Vartotojas(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Vartotojas() {
    }


    public Vartotojas(String login, String password, LocalDate dateCreated, LocalDate lastModified, boolean isActive) {
        this.login = login;
        this.password = password;
        this.dateCreated = dateCreated;
        this.lastModified = lastModified;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Vartotojas{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                ", isActive=" + isActive +
                '}';
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isActive() {
        return isActive;
    }


    public List<Kursas> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Kursas> myCourses) {
        this.myCourses = myCourses;
    }

    public List<Kursas> getCataloguesCreated() {
        return cataloguesCreated;
    }

    public void setCataloguesCreated(List<Kursas> cataloguesCreated) {
        this.cataloguesCreated = cataloguesCreated;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "failas")
public class Failas implements Serializable {

    //@ManyToOne
    //private Katalogas catalogue;

    @ManyToOne
    private Kursas kursas;
    private String fileName;
    private String information;
    private LocalDate dateCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    public Failas(String fileName, String information, LocalDate dateCreated) {
        this.fileName = fileName;
        this.information = information;
        this.dateCreated = dateCreated;
    }

    public Failas(String fileName, String information) {
        this.fileName = fileName;
        this.information = information;
    }

    public Failas() {
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Failas{" +
                "fileName='" + fileName + '\'' +
                ", information='" + information + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}

package com.example.demo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "emissions")//Tabellenname
public class EmissionData implements Serializable {
    @Id //Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-Increment
    private long id;


    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "co2_value")
    private Double value;

    @ManyToOne
    @JoinColumn (name = "creator_id") //Spalte in DB

    private AppUser creator;

    //Leerer Konstruktor
    public EmissionData() {}

    //Erweiterter Konstruktor
    public EmissionData(String country, Integer year, Double co2Value) {
        this.country = country;
        this.year = year;
        this.value = co2Value;
    }

    //Getter und Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }
}

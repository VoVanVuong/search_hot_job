package edu.vku.searchjob.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "migration")
public class Mirgations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "mirgation")
    private String mirgation;
    @Column(name = "batch")
    private String batch;

    public Mirgations() {
    }

    public Mirgations(int id, String mirgation, String batch) {
        this.id = id;
        this.mirgation = mirgation;
        this.batch = batch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMirgation() {
        return mirgation;
    }

    public void setMirgation(String mirgation) {
        this.mirgation = mirgation;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}

package com.example.sales.entities;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Catcode", length = 25)
    private String catcode;

    @Column(name = "Catdesc", length = 25)
    private String catdesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }
}

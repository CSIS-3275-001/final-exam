package com.example.sales.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Catcode", length = 25)
    private String catcode;

    @Column(name = "Icode", length = 25)
    private String icode;

    @Column(name = "Idesc", length = 25)
    private String idesc;

    @Column(name = "Price")
    private Float price;


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

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

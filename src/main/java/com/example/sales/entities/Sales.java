package com.example.sales.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recno")
    private int recno;

    @Column(name = "icode", length = 255)
    private String icode;

    @Column(name = "qty", columnDefinition = "DOUBLE")
    private double qty;

    @Column(name = "dot")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRecno() {
        return recno;
    }

    public void setRecno(int recno) {
        this.recno = recno;
    }

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Date getDot() {
        return dot;
    }

    public void setDot(Date dot) {
        this.dot = dot;
    }
}

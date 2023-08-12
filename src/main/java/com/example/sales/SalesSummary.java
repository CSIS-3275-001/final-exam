package com.example.sales;

public class SalesSummary {
    private String categoryCode;
    private String categoryDesc;
    private double totalSales;

    public SalesSummary(String categoryCode, String categoryDesc, double totalSales) {
        this.categoryCode = categoryCode;
        this.categoryDesc = categoryDesc;
        this.totalSales = totalSales;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}

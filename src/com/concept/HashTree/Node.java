package com.concept.HashTree;

import java.util.HashMap;

public class Node {

    private String product;
    protected HashMap<String, Wrapper> child;
    protected String year;
    protected Double value;

    Node(){
        child = new HashMap<>();
    }

    public HashMap<String, Wrapper> getChild() {
        return child;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}

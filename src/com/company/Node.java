package com.company;

import java.util.HashMap;

public class Node {

    private HashMap<String, Node> child;
    private String year;
    private String product;
    private Double value;

    Node(){
        child = new HashMap<>();
    }

    public HashMap<String, Node> getChild() {
        return child;
    }

    public Node addProductNode(String product){
        Node productNode = new Node();
        productNode.setProduct(product);

        child.put(product, productNode);

        return productNode;
    }

    public Node addMiddleNode(String year){

        Node middleNode = new Node();
        middleNode.setYear(year);

        child.put(year, middleNode);

        return middleNode;
    }

    public Node addBottomNode(int devYear,double value){

        Node bottomNode = new Node();
        bottomNode.setYear(String.valueOf(devYear));
        bottomNode.setValue(value);
        child.put(String.valueOf(devYear), bottomNode);

        return bottomNode;
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

    public boolean exists(String year){

        return child.containsKey(year) ? true : false;
    }
}

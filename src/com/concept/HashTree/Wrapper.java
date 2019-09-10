package com.concept.HashTree;

public class Wrapper extends Node implements ITreeOperations {

    public Node addProductNode(String product){
        Wrapper productNode = new Wrapper();
        productNode.setProduct(product);

        child.put(product, productNode);

        return productNode;
    }

    public Node addMiddleNode(String year){

        Wrapper middleNode = new Wrapper();
        middleNode.setYear(year);

        child.put(year, middleNode);

        return middleNode;
    }

    public Node addBottomNode(int devYear,double value){

        Wrapper bottomNode = new Wrapper();
        bottomNode.setYear(String.valueOf(devYear));
        bottomNode.setValue(value);
        child.put(String.valueOf(devYear), bottomNode);

        return bottomNode;
    }
}

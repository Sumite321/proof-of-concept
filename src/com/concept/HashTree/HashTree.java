package com.concept.HashTree;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashTree {

    private Wrapper root;
    private StringBuilder builder;

    public void readDataIntoMemory(){

        root = new Wrapper();

        buildRow("Non-Comp","1990",1990,45.2);
        buildRow("Non-Comp","1990",1991,64.8);
        buildRow("Non-Comp","1990",1993,37.0);
        buildRow("Non-Comp","1991",1991,50.0);
        buildRow("Non-Comp","1991",1992,75.0);
        buildRow("Non-Comp","1991",1993,25.0);
        buildRow("Non-Comp","1992",1992,55.0);
        buildRow("Non-Comp","1992",1993,85.0);
        buildRow("Non-Comp","1993",1993,100.0);

        System.out.println(computate());
    }

    private void buildRow(String product, String originYear, int devYear, double value){


        if(!root.getChild().containsKey(product)) {
            root.addProductNode(product);
        }

        if(!root.getChild().get(product).getChild().containsKey(originYear)) {
            root.getChild().get(product).addMiddleNode(originYear);
        }

        root.getChild().get(product).getChild().get(originYear).addBottomNode(devYear,value);

    }

    private String verifyYearsAndAccumulate(String product, String originYear){

        int previous = 0;
        boolean firstRun = true;
        boolean yearMissing = false;
        StringBuilder accumulatedRow = new StringBuilder();
        double sum = 0;
        Node usableObject;

        HashMap<String, Wrapper> map = root.getChild().get(product).getChild().get(originYear).getChild();

        SortedSet<String> keys = new TreeSet<>(map.keySet());
        for (String key : keys) {
            usableObject = map.get(key);

            if(previous == Integer.valueOf(usableObject.getYear()) - 2 && !firstRun){
                yearMissing = true;
            }

            if(firstRun){
                accumulatedRow.append(usableObject.getValue() );
                sum += usableObject.getValue();
            }else if (!yearMissing){
                sum += usableObject.getValue();
                accumulatedRow.append("," + sum );
            }else{
                accumulatedRow.append("," + sum + "," + (sum += usableObject.getValue()));
                yearMissing = false;
            }

            previous = Integer.valueOf(usableObject.getYear());
            firstRun = false;
        }

        return accumulatedRow.toString();
    }

    public String computate(){

        builder = new StringBuilder();
        Node usableObject;

        SortedSet<String> keys;

        if(!root.getChild().isEmpty()){

            for(Node product : root.getChild().values()){
                builder.append(product.getProduct() );

                if(!product.getChild().isEmpty()){

                    keys = new TreeSet<>(product.getChild().keySet());
                    for (String key : keys) {
                        usableObject = product.getChild().get(key);

                        if (!usableObject.getChild().values().isEmpty()) {
                            builder.append("," + verifyYearsAndAccumulate(product.getProduct(), usableObject.getYear()));
                        }
                    }
                }
                builder.append("|");
            }
        }
        return builder.toString();
    }

    public void readTree(){

        System.out.println(root.getChild());

        for(Node product: root.getChild().values()){

            System.out.println(product.getProduct());

            for(Node originYear: product.getChild().values()) {

                System.out.println(originYear.getYear());

                for (Node devYear : originYear.getChild().values()) {

                    System.out.println(devYear.getYear() + " " + devYear.getValue());
                }
            }
        }

    }

}

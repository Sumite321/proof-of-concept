package com.company;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    static Node root;


    public static void main(String[] args) {
	// write your code here

        root = new Node();

/*
        buildRow("Comp","1992",1992,110.0);
        buildRow("Comp","1992",1993,170.0);
        buildRow("Comp","1993",1993,45.2);
        //buildRow("Comp","1993",1996,46.3);
*/


        buildRow("Non-Comp","1990",1990,45.2);
        buildRow("Non-Comp","1990",1991,64.8);
        buildRow("Non-Comp","1990",1993,37.0);
        buildRow("Non-Comp","1991",1991,50.0);
        buildRow("Non-Comp","1991",1992,75.0);
        buildRow("Non-Comp","1991",1993,25.0);
        buildRow("Non-Comp","1992",1992,55.0);
        buildRow("Non-Comp","1992",1993,85.0);
        buildRow("Non-Comp","1993",1993,100.0);

        System.out.println(performAction());


        //System.out.println(accumulateRow("Comp", "1993"));
        //System.out.println(verifyYearsInOrder("Comp", "1993"));

        //readTree();
    }

    public static void readTree(){

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

    public static void buildRow(String product, String originYear, int devYear, double value){

        if(!root.getChild().containsKey(product)) {
            root.addProductNode(product);
        }

        if(!root.getChild().get(product).getChild().containsKey(originYear)) {
            root.getChild().get(product).addMiddleNode(originYear);
        }

        root.getChild().get(product).getChild().get(originYear).addBottomNode(devYear,value);
    }

    public static Double accumulateRow(String product, String originYear){

        double sum = 0;

        for(Node bottomNode : root.getChild().get(product).getChild().get(originYear).getChild().values()){

            sum += bottomNode.getValue();
        }

        return sum;
    }

    public static String verifyYearsInOrder(String product, String originYear){

        int previous = 0;
        boolean verified = true;
        boolean firstRun = true;
        boolean yearMissing = false;
        StringBuilder accumulatedRow = new StringBuilder();
        double sum = 0;
        Node usableObject;

        HashMap<String, Node> map = root.getChild().get(product).getChild().get(originYear).getChild();

        SortedSet<String> keys = new TreeSet<>(map.keySet());
        for (String key : keys) {
            usableObject = map.get(key);

            if(previous == Integer.valueOf(usableObject.getYear()) - 2 && !firstRun){
                yearMissing = true;
                //System.out.println("year missing");
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
            //System.out.println(usableObject.getYear());
            firstRun = false;
        }

        /*
        for(Node bottomNode : root.getChild().get(product).getChild().get(originYear).getChild().values()){

            if(previous == Integer.valueOf(bottomNode.getYear()) + 2 && !firstRun){
                verified = false;
                yearsMissing.append(previous - 1+","+bottomNode.getValue()+"|");
            }

            previous = Integer.valueOf(bottomNode.getYear());
            System.out.println(bottomNode.getYear());
            firstRun = false;
        }*/

        return accumulatedRow.toString();

    }

    public static String performAction(){

        StringBuilder builder = new StringBuilder();
        Node usableObject;

        if(!root.getChild().isEmpty()){

            for(Node product : root.getChild().values()){
                builder.append(product.getProduct() );

                if(!product.getChild().isEmpty()){

                    SortedSet<String> keys = new TreeSet<>(product.getChild().keySet());
                    for (String key : keys) {
                        usableObject = product.getChild().get(key);

                         if (!usableObject.getChild().values().isEmpty()) {
                                builder.append("," + verifyYearsInOrder(product.getProduct(), usableObject.getYear()));
                                //System.out.println(verifyYearsInOrder(product.getProduct(),originYear.getYear()));
                            }

                    }
                }
                builder.append("|");
            }
        }
        return builder.toString();
        }
    }




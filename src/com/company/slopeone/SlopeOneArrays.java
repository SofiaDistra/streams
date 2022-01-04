package com.company.slopeone;

import java.util.*;

public class SlopeOneArrays {

    protected static List<Item> items = Arrays.asList(
            new Item("Candy"),
            new Item("Drink"),
            new Item("Soda"),
            new Item("Popcorn"),
            new Item("Snacks"));

    private static Double[][] inputDataArray;
    private static Double[][] diffArray;
    private static Integer[][] freqArray;

    private static Double[] uPred;
    private static Integer[] uFreq;
    private static Double[][] outPutDataArray;
    private static int numberOfItems = 5;

    public SlopeOneArrays(int numberOfUsers) {
        initializeData(numberOfUsers);
        buildDiffMatrix();
        predict();
    }

    public static void initializeData(int numberOfUsers) {
        inputDataArray = new Double[numberOfUsers][5];
        outPutDataArray = new Double[numberOfUsers][5];


        uPred = new Double[numberOfItems];
        uFreq = new Integer[numberOfItems];

        int rating = 0;
        int position;
        for (int i = 0; i < numberOfUsers; i++) {
            // 3 ratings for each user
            for(int j=0;j<3;j++) {
                rating = 0;
                while(rating == 0) {
                    rating = (int) (Math.random() * 10);
                }
                position = items.indexOf(items.get((int) (Math.random() * numberOfItems)));
                inputDataArray[i][position] = (double)rating;
            }
        }

        initDataArray();

    }

    private static void initDataArray() {

        diffArray = new Double[numberOfItems][numberOfItems];
        freqArray = new Integer[numberOfItems][numberOfItems];

        for(int i=0; i<numberOfItems; i++) {

            uPred[i] = 0.0;
            uFreq[i] = 0;

            for(int j=0; j<numberOfItems; j++) {
                diffArray[i][j] = 0.0;
                freqArray[i][j] = 0;
            }
        }

        for(int i=0;i<inputDataArray.length;i++) {
            for(int j=0;j<inputDataArray[i].length;j++) {
                if(inputDataArray[i][j] == null) {
                    inputDataArray[i][j] = 0.0;
                }
                if(outPutDataArray[i][j] == null) {
                    outPutDataArray[i][j] = 0.0;
                }
            }
        }
    }

    public static void buildDiffMatrix() {

        // for each user
        for(int i=0; i<inputDataArray.length; i++) {
            // for each item the user has rated
            for(int j=0; j<inputDataArray[i].length; j++) {
                // (unrated items have negative values)
                if(inputDataArray[i][j] > 0) {

                    for(int k=0;k<inputDataArray.length;k++) {
                        for(int l=0; l<inputDataArray[k].length;l++) {
                            if(inputDataArray[k][l] > 0 && i==k) {
                                freqArray[j][l]++;
                                diffArray[j][l] += inputDataArray[i][j] - inputDataArray[k][l];
                            }
                        }
                    }

                }
            }
        }

        // normalize
        for (int i=0; i<diffArray.length; i++) {
            for (int j=0; j<diffArray[i].length; j++) {
                double oldValue = diffArray[i][j];
                int count = freqArray[i][j];
                if(count!=0) {
                    diffArray[i][j] = oldValue/count;
                }
            }
        }

        System.out.println("Showing Diff Array");
        System.out.println("------------------");
        for (int i=0; i<diffArray.length; i++) {
            for (int j=0; j<diffArray[i].length; j++) {
                System.out.print(diffArray[i][j] + " ");
            }

            System.out.println("");
        }

        System.out.println("Showing Freq Array");
        System.out.println("------------------");
        for (int i=0; i<freqArray.length; i++) {
            for (int j=0; j<freqArray[i].length; j++) {
                System.out.print(freqArray[i][j] + " ");
            }

            System.out.println("");
        }

    }

    public static void predict() {

        /*int user = 0;
        int item = 0;
        double result;

        double origValue = inputDataArray[user][item];

        if(origValue!=0) {
            for(int k=0;k<diffArray.length;k++) {
                double diffValue = diffArray[k][item];
                if(diffValue==0 && k!=item) continue;
                double predictedValue = diffValue +origValue;

                int freqValue = freqArray[k][item];
                double finalValue = predictedValue * freqValue;

                uPred[k] += finalValue;
                uFreq[k] += freqValue;
            }

            result = uPred[item] / uFreq[item];
            System.out.println("User " + user + " rating for item " + item + " = " + result);

        }
        else {
            System.out.println("Item already rated");
        }*/

        for(int i=0; i<inputDataArray.length;i++) {
            for(int j=0; j<inputDataArray[i].length;j++) {
                double origValue = inputDataArray[i][j];

                if(origValue!=0) {
                    for(int k=0;k<diffArray.length;k++) {
                        double diffValue = diffArray[j][k];
                        if(diffValue==0 && k!=j) continue;
                        double predictedValue = diffValue +origValue;

                        int freqValue = freqArray[j][k];
                        double finalValue = predictedValue * freqValue;

                        uPred[k] += finalValue;
                        uFreq[k] += freqValue;
                    }
                }

            }

            for(int j=0;j<outPutDataArray[i].length;j++) {

                if(inputDataArray[i][j] == 0) {
                    outPutDataArray[i][j] = uPred[j] / uFreq[j];
                }
                else {
                    outPutDataArray[i][j] = inputDataArray[i][j];
                }
            }
        }

        System.out.println("Showing Predictions");
        System.out.println("-------------------");

        for (int i=0; i<outPutDataArray.length; i++) {
            for (int j=0; j<outPutDataArray[i].length; j++) {
                System.out.print(outPutDataArray[i][j] + " ");
            }

            System.out.println("");
        }

    }
}

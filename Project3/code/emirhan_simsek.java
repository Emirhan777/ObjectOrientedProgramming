//Emirhan Şimşek 2021402189 10.05.2023
//This code fraws a map and pours money until the map is full of money
//Lakes are created and labeled, score calculated by taking sqrts of volumes


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class emirhan_simsek {

    public static void main(String[] args) throws FileNotFoundException {

        int numRows=26*27;
        int numColumns=0;


        Scanner input = new Scanner(System.in);  // Create a Scanner object

        //read file
        File file = new File("input.txt");
        Scanner inputFile = new Scanner(file);

        Environment environment=new Environment();

        //while loop for creating environment
        int i=0;
        while (inputFile.hasNextLine() && i<numRows+1){
            String readLine = inputFile.nextLine(); //read text line by line
            String[] lineSplit = readLine.split(" "); //turn line to int array

            //read number of rows and columns
            if(i==0){
                numRows=Integer.parseInt(lineSplit[1]);
                numColumns=Integer.parseInt(lineSplit[0]);
                environment.heights=new int[numRows][numColumns];
                environment.lakeLetterLabels=new String[numRows][numColumns];
                environment.pileOfMoneyMatrix=new int[numRows][numColumns];
                environment.pileOfMoneyCheckedMatrix=new int[numRows][numColumns];
            }

            //place heights to arraylist of arrays of environment object
            else {
                int[] rowHeights= new int[numColumns];
                for (int j = 0; j < numColumns; j++) {
                    rowHeights[j]=Integer.parseInt(lineSplit[j]);
                }
                environment.heights[i-1]=rowHeights;
            }
            i+=1;
        }

        for (int k = 0; k < environment.heights.length; k++) {
            if(k<10){System.out.print("  "+k+" ");}
            else {System.out.print(" "+k+" ");}

            for (int j = 0; j < environment.heights[k].length; j++) {
                if(environment.heights[k][j]<10) {System.out.print( " "+environment.heights[k][j]+" ");}
                else{System.out.print( environment.heights[k][j]+" ");}
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int j = 0; j < environment.heights[1].length; j++) {
            if(j<26){System.out.print(" "+environment.coordinatesList[j]+" ");}
            else{System.out.print(environment.coordinatesList[j]+" ");}
        }

        //add 10 stones to the map

        while(environment.counterUpToTen<11){
            System.out.println();
            System.out.print("Add stone "+ environment.counterUpToTen +" / 10 to coordinate:");
            String inputCoordinate = input.nextLine();  // Read user input
            environment.addStone(inputCoordinate);
        }

        environment.floadEverywhere();
        environment.setPileOfMoneyCheckedMatrixPutANumberForEveryDifferentPile();

        System.out.println();

        environment.drawEnvironmentLakeLetterLabels();


//        System.out.println();
//
//        for (int j = 0; j < environment.pileOfMoneyMatrix.length; j++) {
//            for (int k = 0; k < environment.pileOfMoneyMatrix[0].length; k++) {
//                System.out.print(environment.pileOfMoneyMatrix[j][k]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        for (int j = 0; j < environment.pileOfMoneyCheckedMatrix.length; j++) {
//            for (int k = 0; k < environment.pileOfMoneyCheckedMatrix[0].length; k++) {
//                System.out.print(environment.pileOfMoneyCheckedMatrix[j][k]);
//            }
//            System.out.println();
//        }

//        for (int j = 0; j < environment.sumList.length; j++) {
//            System.out.println(environment.sumList[j]);
//        }

        environment.calculateAndPrintScore();




    }


}
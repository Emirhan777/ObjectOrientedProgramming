//Emirhan Şimşek 2021402189 10.05.2023




public class Environment  {

    //define all the maps, lists, matrices and variables
    int[][] pileOfMoneyMatrix= new int[26*27][26*27];
    int[][] pileOfMoneyCheckedMatrix= new int[26*27][26*27];

    int counterUpToTen=1;

    String[] coordinatesList = new String[26*27];
    String[] lakePossibleLabelsList = new String[26*27];


    int[][] heights= new int[26*27][];
    int[] sumList = new int[26*27*26*27];

    String[][] lakeLetterLabels = new String[26*27][];
    int nextLetter=0;

    //initialise envirenment
    public Environment(){
        int numCounter=0;
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 26; j++) {
                if (i==0) {
                    coordinatesList[numCounter] = Character.toString((char)(j+97));
                    lakePossibleLabelsList[numCounter] = Character.toString((char)(j+65));
                }
                else {
                    coordinatesList[numCounter] = Character.toString((char)(i+96))+(char)(j+97);
                    lakePossibleLabelsList[numCounter] = Character.toString((char)(i+64))+(char)(j+65);
                }
                numCounter++;
            }
        }
        //System.out.println("coordinatesList: " + Arrays.toString(coordinatesList));
        //System.out.println("lakePossibleLabelsList: " + Arrays.toString(lakePossibleLabelsList));
    }

    //draw environment's current state (with money if added)
    public void drawEnvironment() {
        for (int i = 0; i < heights.length; i++) {
            if(i<10){System.out.print("  "+i+" ");}
            else {System.out.print(" "+i+" ");}

            for (int j = 0; j < heights[i].length; j++) {
                System.out.print( " "+heights[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int j = 0; j < heights[1].length; j++) {
            if(j<26){System.out.print(" "+coordinatesList[j]+" ");}
            else{System.out.print(coordinatesList[j]+" ");}
        }
    }

//draw environment with its letter labels for the lakes
    public void drawEnvironmentLakeLetterLabels(){
        //System.out.println("print labels");

        for (int i = 0; i < pileOfMoneyCheckedMatrix.length; i++) {
            if(i<10){System.out.print("  "+i+" ");}
            else {System.out.print(" "+i+" ");}

            for (int j = 0; j < pileOfMoneyCheckedMatrix[i].length; j++) {
                if(pileOfMoneyCheckedMatrix[i][j]!=0){
                    if(pileOfMoneyCheckedMatrix[i][j]<27){System.out.print( " "+lakePossibleLabelsList[pileOfMoneyCheckedMatrix[i][j]-1]+" ");}
                    else{System.out.print( lakePossibleLabelsList[pileOfMoneyCheckedMatrix[i][j]-1]+" ");}
                    }
                else{
                    if(heights[i][j]<10){System.out.print(" "+heights[i][j]+" ");}
                    else{System.out.print(heights[i][j]+" ");}
                }
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int j = 0; j < pileOfMoneyCheckedMatrix[1].length; j++) {
            if(j<26){System.out.print(" "+coordinatesList[j]+" ");}
            else{System.out.print(coordinatesList[j]+" ");}
        }
    }


//add stone to the map as input is taken
    public void addStone(String s){
        //System.out.println("add: "+s);
        String a=null;
        String b=null;
        int stoneCoordinateX=0;


        if(s.length()<2){System.out.print("Not a valid step!");}

        else if ((int)s.charAt(0)>96 && (int)s.charAt(0)<123 && (int)s.charAt(1)>96 && (int)s.charAt(1)<123) {
            a=s.substring(0,2);
            b=s.substring(2);
            for (int i = 0; i < coordinatesList.length; i++) {
                if(coordinatesList[i].equals(a)){
                    //ystem.out.println("i: "+i);
                    stoneCoordinateX=i;
                }
            }
            try {
                int stoneCoordinateY = Integer.parseInt(b);
                //System.out.println(b + " is an integer.");
                heights[stoneCoordinateY][stoneCoordinateX]++;
                counterUpToTen++;
                drawEnvironment();
                System.out.println();
                System.out.print("---------------");
            } catch (NumberFormatException e) {
                System.out.print("Not a valid step!");
            }

        }

        else if ((int)s.charAt(0)>96 && (int)s.charAt(0)<123) {
            a=s.substring(0,1);
            b=s.substring(1);
            for (int i = 0; i < coordinatesList.length; i++) {
                if(coordinatesList[i].equals(a)){
                    //System.out.println("i: "+i);
                    stoneCoordinateX=i;
                }
            }
            try {
                int stoneCoordinateY = Integer.parseInt(b);
                //System.out.println(b + " is an integer.");
                heights[stoneCoordinateY][stoneCoordinateX]++;
                counterUpToTen++;
                drawEnvironment();
                System.out.println();
                System.out.print("---------------");
            } catch (NumberFormatException e) {
                System.out.print("Not a valid step!");
            }
        }
        else {

            System.out.print("Not a valid step!");
        }

        //System.out.println("a and b: "+a+" "+b);


    }

//flow every index
    public void floadEverywhere(){
        for (int i = 1; i < heights.length-1; i++) {
            for (int j = 1; j < heights[0].length-1; j++) {
                floadWithMoney(j,i);
            }
        }
    }

    int numLakes=0;
    int sum=0;
    int coordinateX=1;
    int coordinateY=1;


    int nextLetterForAPouringCoordinate=nextLetter;
    public void floadWithMoney(int pouringCoordinateX, int pouringCoordinateY){
        //System.out.println();
        //drawEnvironment();
        coordinateX=pouringCoordinateX;
        coordinateY=pouringCoordinateY;
        double[][] localMatrixForBeingThereOnce = new double[heights.length][heights[0].length]; //refreshed every time a box of money is poured
//        System.out.println();
//        System.out.println();
        //System.out.println("new box money "+coordinatesList[coordinateX]+" "+coordinateY);
        //drawEnvironmentLakeLetterLabels();

        heights[coordinateY][coordinateX]++;
        sum++;

        nextLetter=nextLetterForAPouringCoordinate;

        boolean newLake=false;
        while(coordinateX!=0 && coordinateX!=heights[0].length-1 && coordinateY!=0 && coordinateY!=heights.length-1 ) {
            //System.out.println();
            //System.out.println("while "+coordinatesList[coordinateX]+" "+coordinateY);
            int temporaryCoordinateX=coordinateX;
            int temporaryCoordinateY=coordinateY;

            if(lakeLetterLabels[coordinateY][coordinateX]!=null){
                for (int i = 0; i < lakePossibleLabelsList.length; i++) {
                    if(lakePossibleLabelsList[i].equals(lakeLetterLabels[coordinateY][coordinateX])){nextLetter=i;}
                }
            }

            //check if the box can move down
            if (heights[coordinateY-1][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX-1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX-1]++;heights[coordinateY][coordinateX]--;coordinateY--;coordinateX--;}
            else if (heights[coordinateY-1][coordinateX] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX]++;heights[coordinateY][coordinateX]--;coordinateY--;}
            else if (heights[coordinateY-1][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX+1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX+1]++;heights[coordinateY][coordinateX]--;coordinateY--;coordinateX++;}
            else if (heights[coordinateY][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY][coordinateX-1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY][coordinateX-1]++;heights[coordinateY][coordinateX]--;coordinateX--;}
            else if (heights[coordinateY][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY][coordinateX+1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY][coordinateX+1]++;heights[coordinateY][coordinateX]--;coordinateX++;}
            else if (heights[coordinateY+1][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX-1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ){heights[coordinateY+1][coordinateX-1]++;heights[coordinateY][coordinateX]--;coordinateY++;coordinateX--;}
            else if (heights[coordinateY+1][coordinateX] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY+1][coordinateX]++;heights[coordinateY][coordinateX]--;coordinateY++;}
            else if (heights[coordinateY+1][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX+1] < heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY+1][coordinateX+1]++;heights[coordinateY][coordinateX]--;coordinateY++;coordinateX++;}

            else if (heights[coordinateY-1][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX-1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX-1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY--;coordinateX--;}
            else if (heights[coordinateY-1][coordinateX] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY--;}
            else if (heights[coordinateY-1][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY-1][coordinateX+1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY-1][coordinateX+1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY--;coordinateX++;}
            else if (heights[coordinateY][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY][coordinateX-1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY][coordinateX-1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateX--;}
            else if (heights[coordinateY][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY][coordinateX+1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY][coordinateX+1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateX++;}
            else if (heights[coordinateY+1][coordinateX-1] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX-1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ){heights[coordinateY+1][coordinateX-1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY++;coordinateX--;}
            else if (heights[coordinateY+1][coordinateX] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY+1][coordinateX]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY++;}
            else if (heights[coordinateY+1][coordinateX+1] + localMatrixForBeingThereOnce[coordinateY+1][coordinateX+1] == heights[coordinateY][coordinateX]-1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] ) {heights[coordinateY+1][coordinateX+1]++;heights[coordinateY][coordinateX]--;localMatrixForBeingThereOnce[coordinateY][coordinateX]+=0.5; coordinateY++;coordinateX++;}

            //check if the box can move sideways (on the same level)
            if(temporaryCoordinateX==coordinateX && temporaryCoordinateY==coordinateY) {
//                System.out.println("Not moving one unit around");
                outerloop:
                for (int i = 0; i < heights.length; i++) {
                    for (int j = 0; j < heights[0].length; j++) {
                        if (i == 0 && j == 0) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    (localMatrixForBeingThereOnce[i + 1][j + 1] == 0.5)) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (i == 0 && j == heights[0].length - 1) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    (localMatrixForBeingThereOnce[i + 1][j - 1] == 0.5)) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (i == heights.length - 1 && j == 0) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    (localMatrixForBeingThereOnce[i - 1][j + 1] == 0.5)) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (i == heights.length - 1 && j == heights[0].length - 1) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    (localMatrixForBeingThereOnce[i - 1][j - 1] == 0.5)) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (i == 0) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    ((localMatrixForBeingThereOnce[i + 1][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j + 1] == 0.5))) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (i == heights.length - 1) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    ((localMatrixForBeingThereOnce[i - 1][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i - 1][j] == 0.5) || (localMatrixForBeingThereOnce[i - 1][j + 1] == 0.5))) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (j == 0) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    ((localMatrixForBeingThereOnce[i - 1][j + 1] == 0.5) || (localMatrixForBeingThereOnce[i][j + 1] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j + 1] == 0.5))) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else if (j == heights[0].length - 1) {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    ((localMatrixForBeingThereOnce[i - 1][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j - 1] == 0.5))) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        } else {
                            if (heights[i][j] + localMatrixForBeingThereOnce[i][j] == heights[coordinateY][coordinateX] - 1 + localMatrixForBeingThereOnce[coordinateY][coordinateX] &&
                                    ((localMatrixForBeingThereOnce[i - 1][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i - 1][j] == 0.5) || (localMatrixForBeingThereOnce[i - 1][j + 1] == 0.5) || (localMatrixForBeingThereOnce[i][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i][j + 1] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j - 1] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j] == 0.5) || (localMatrixForBeingThereOnce[i + 1][j + 1] == 0.5))) {
                                //System.out.println("move pam " + j + " " + i);
                                heights[i][j]++;
                                heights[coordinateY][coordinateX]--;
                                localMatrixForBeingThereOnce[coordinateY][coordinateX] += 0.5;
                                coordinateY = i;
                                coordinateX = j;
                                break outerloop;
                            }
                        }
                    }
                }
            }

            //if box can not move, break the while loop for moving the box, this is the end of the motion of the box
            if(temporaryCoordinateX==coordinateX && temporaryCoordinateY==coordinateY){

//                if(newLake){
//                    System.out.println("Next Letter");
//                    nextLetter++;
//                    numLakes++;
//                    sumList.add(sum);
//                    sum=1;
//                    newLake = false;
//                }

                lakeLetterLabels[coordinateY][coordinateX]=lakePossibleLabelsList[nextLetter];
                //System.out.println("add money and break the while loop for a box");

                pileOfMoneyMatrix[coordinateY][coordinateX]++;
                break;
            }

            /////////////////////////
//            drawEnvironment();
//            System.out.println();
//
//            for (int i = 0; i < localMatrixForBeingThereOnce.length; i++) {
//                for (int j = 0; j < localMatrixForBeingThereOnce[0].length; j++) {
//                    System.out.print(localMatrixForBeingThereOnce[i][j]+" ");
//                }
//                System.out.println();
//            }
            ////////////////////////

        }
        if(coordinateX==0 || coordinateX==heights[0].length-1 || coordinateY==0 || coordinateY==heights.length-1){
            //System.out.println("Flow out: "+coordinatesList[coordinateX]+" "+coordinateY);
            sum--;
            heights[coordinateY][coordinateX]--;

            //System.out.println("Sum before floating out: "+sum);
            if(sum!=0){
                nextLetter++;
                numLakes++;
                //sumList.add(sum);
                sum=0;
                //System.out.println("I return the recursive function and add the sum, numLakes: " +numLakes);
                //System.out.println("This is sumList: "+sumList);
            }
            nextLetterForAPouringCoordinate=nextLetter;


            return;
        }



//        System.out.println();
//        System.out.println("Sum: "+sum);
//
//        System.out.println();
//        drawEnvironment();
//
//        System.out.println();
//        drawEnvironmentLakeLetterLabels();
//
//        System.out.println();

        floadWithMoney(pouringCoordinateX,pouringCoordinateY);
    }

    //calculate score by sqrt of volumes
    public void calculateAndPrintScore(){
        double score=0.0;
        for (int i = 0; i < sumList.length; i++) {
            score+=Math.sqrt(sumList[i]);
        }

        System.out.println();
        System.out.print("Final score: ");
        System.out.format("%.2f", score);
    }


//set a new clean map to mark every money lake label
    public void setPileOfMoneyCheckedMatrixPutANumberForEveryDifferentPile(){
        int lakeNumber=1;
        for (int i = 0; i < pileOfMoneyCheckedMatrix.length; i++) {
            for (int j = 0; j < pileOfMoneyCheckedMatrix[0].length; j++) {

                int area=0;


                if(pileOfMoneyMatrix[i][j]!=0 && pileOfMoneyCheckedMatrix[i][j]==0){
                    pileOfMoneyCheckedMatrix[i][j]=lakeNumber;
                    sumList[lakeNumber-1]=sumList[lakeNumber-1]+pileOfMoneyMatrix[i][j];
                    
                    
                    while (true) {
                        int areaTemporary=area;
                        for (int k = 0; k < pileOfMoneyMatrix.length; k++) {
                            for (int l = 0; l < pileOfMoneyMatrix[0].length; l++) {
                                if ( pileOfMoneyMatrix[k][l]!=0 && pileOfMoneyCheckedMatrix[k][l]==0 &&
                                        ((pileOfMoneyCheckedMatrix[k - 1][l - 1] == lakeNumber) || (pileOfMoneyCheckedMatrix[k - 1][l] == lakeNumber) || (pileOfMoneyCheckedMatrix[k - 1][l + 1] == lakeNumber) || (pileOfMoneyCheckedMatrix[k][l - 1] == lakeNumber) || (pileOfMoneyCheckedMatrix[k][l + 1] == lakeNumber) || (pileOfMoneyCheckedMatrix[k + 1][l - 1] == lakeNumber) || (pileOfMoneyCheckedMatrix[k + 1][l] == lakeNumber) || (pileOfMoneyCheckedMatrix[k + 1][l + 1] == lakeNumber))) {
                                    pileOfMoneyCheckedMatrix[k][l]=lakeNumber;
                                    area++;
                                    sumList[lakeNumber-1]=sumList[lakeNumber-1]+pileOfMoneyMatrix[k][l];

                                }
                            }

                        }
                        if(area==areaTemporary){
                            //System.out.println("area: "+area);
                            lakeNumber++;
                            break;
                        }


//                        System.out.println();
//                        for (int z = 0; z < pileOfMoneyCheckedMatrix.length; z++) {
//                            for (int k = 0; k < pileOfMoneyCheckedMatrix[0].length; k++) {
//                                System.out.print(pileOfMoneyCheckedMatrix[z][k]);
//                            }
//                            System.out.println();
//                        }
                    }

                }
            }

        }
    }


}



//else if (heights[coordinateY][coordinateX+1] == heights[coordinateY][coordinateX]) {System.out.println("taştı eklemeden breakledim");heights[coordinateY][coordinateX]--;break;}


//    String theCurrentLakeLetterString=lakePossibleLabelsList[nextLetter];
//                if(lakeLetterLabels[coordinateY][coordinateX]==null){
//                    System.out.println(lakeLetterLabels[coordinateY][coordinateX]);
//                    System.out.println(theCurrentLakeLetterString);
//                    nextLetter--;
//                    numLakes--;
//
//                }
//                else if(lakeLetterLabels[coordinateY][coordinateX].equals(theCurrentLakeLetterString)){
//                    System.out.println(lakeLetterLabels[coordinateY][coordinateX]);
//                    System.out.println(theCurrentLakeLetterString);
//                    nextLetter--;
//                    numLakes--;
//                    sumList.set(sumList.size()-2,sumList.get(sumList.size()-2)+sumList.get(sumList.size()-1));
//                    sumList.remove(sumList.size()-1);
//                }

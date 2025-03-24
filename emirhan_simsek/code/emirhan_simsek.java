/*Emirhan Şimşek 2021402189 26 March 2023
 This code retrieves information such as station names and coordinate data from a txt file,
 takes starting point and ending point and finds a path, prints station names on the path
 and visualises the metro path on a png picture
 or prints out a does not exists message
*/


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class emirhan_simsek {

    public static void main(String[] args) throws FileNotFoundException {

        //int numStations = 154;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //    System.out.print("From");
        String from = myObj.nextLine();  // Read user input
        //    System.out.print("To");
        String to = myObj.nextLine();

        String fileName = "coordinates.txt";
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        //initialisation
        String nameLine ;
        Color color ;

        boolean printOrNot ; // Initialize to default value
        String nameStation ;
        double X ;
        double Y ;

        boolean fromExists=false;
        boolean toExists=false;


        int i = 0;
        //    int stationCounter = 0;

        ArrayList<String> nameLines = new ArrayList<>(); //initialise arraysi array lists and array of arraylists
        ArrayList<Color> colors = new ArrayList<>();

        ArrayList<Boolean>[] printOrNots = new ArrayList[10];
        ArrayList<String>[] nameStations = new ArrayList[10];
        ArrayList<Double>[] xs = new ArrayList[10];
        ArrayList<Double>[] ys = new ArrayList[10];

        ArrayList<String> breakpointStations = new ArrayList<>();
        ArrayList<String>[] breakpointStationsLines = new ArrayList[7];

        if (!file.exists()) {
            System.out.printf("%s can not be found.", fileName);
            System.exit(1);
        }



        while (inputFile.hasNextLine() && i < 20) {
            String readLine = inputFile.nextLine(); //read text line by line
            String[] lineSplit = readLine.split(" ");

            if (i % 2 == 1) {
                printOrNots[(i - 1) / 2] = new ArrayList<Boolean>();
                printOrNots[(i - 1) / 2] = new ArrayList<Boolean>();
                nameStations[(i - 1) / 2] = new ArrayList<String>();
                xs[(i - 1) / 2] = new ArrayList<Double>();
                ys[(i - 1) / 2] = new ArrayList<Double>();
            }

            for (int j = 0; j < lineSplit.length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    nameLine = lineSplit[j];
                    nameLines.add(nameLine); //add name to the arraylist in nameLines array

                } else if (i % 2 == 0) {
                    String[] colorLineSplit = lineSplit[j].split(",");

                    int R = Integer.parseInt(colorLineSplit[0]);
                    int G = Integer.parseInt(colorLineSplit[1]);
                    int B = Integer.parseInt(colorLineSplit[2]);
                    color = new Color(R, G, B);
                    colors.add(color);

                } else if (i % 2 == 1 && j % 2 == 0) {
                    //        stationCounter++;
                    char asterisk = lineSplit[j].charAt(0);
                    String asterisk2 = "" + asterisk;
                    if (asterisk2.equals("*")) {
                        printOrNot = true;
                        printOrNots[(i - 1) / 2].add(printOrNot);
                        nameStation = lineSplit[j].replace("*", "");
                        nameStations[(i - 1) / 2].add(nameStation);
                    } else {
                        printOrNot = false;
                        printOrNots[(i) / 2].add(printOrNot);
                        nameStation = lineSplit[j];
                        nameStations[(i - 1) / 2].add(nameStation);
                    }
                } else if (i % 2 == 1) {
                    String[] coordinatesLineSplit = lineSplit[j].split(",");
                    X = Double.parseDouble(coordinatesLineSplit[0]);
                    Y = Double.parseDouble(coordinatesLineSplit[1]);
                    xs[(i - 1) / 2].add(X);
                    ys[(i - 1) / 2].add(Y);

                }


            }
            i++;
        }
        while (inputFile.hasNextLine() && i >= 20) {
            breakpointStationsLines[i-20] = new ArrayList<String>();
            String readLine = inputFile.nextLine();
            String[] lineSplit = readLine.split(" ");
            breakpointStations.add(lineSplit[0]);
            for (int z = 0; z < lineSplit.length-1; z++) {
                breakpointStationsLines[i-20].add(lineSplit[z+1]);
            }
            i++;
        }

        String lightedStation = null;
        ArrayList<String> path = new ArrayList<>();



        int indexi = 0;
        int indexj = 0;


        for (int k = 0; k < 10; k++) {
            for (int m = 0; m < nameStations[k].size(); m++) {
                if (nameStations[k].get(m).equals(from)) {
                    indexi = k;
                    indexj = m;

                    fromExists=true;
                    lightedStation = nameStations[indexi].get(indexj);

                }
            }
        }
        path.add(lightedStation);

        for (int k = 0; k < 10; k++) {
            for (int m = 0; m < nameStations[k].size(); m++) {
                if (nameStations[k].get(m).equals(to)) {
                    toExists=true;
                }
            }
        }

        boolean foundnotfound = false;


        //checking if the line i has been visited froward and backward, if visited backward all stations in line i visited
        Boolean[] forward_ended_indicesi = new Boolean[10];
        Boolean[] backward_ended_indicesi = new Boolean[10];
        for (int t = 0; t < forward_ended_indicesi.length; t++) {
            forward_ended_indicesi[t]=false;
            backward_ended_indicesi[t]=false;
        }


        if(fromExists && toExists) {

            while (!foundnotfound) {


                if (lightedStation.equals(to)) {
                    foundnotfound = true;
                    //            System.out.println(f + ". Loop : " + path);
                    continue;
                } else if (!forward_ended_indicesi[indexi] && indexj + 1 == nameStations[indexi].size()) {
                    forward_ended_indicesi[indexi] = true;
                    //            System.out.println("a");
                    //            System.out.println(f + ". Loop : " + path);
                    continue;
                } else if (!backward_ended_indicesi[indexi] && indexj == 0) {
                    backward_ended_indicesi[indexi] = true;
                    //            System.out.println("b");
                    //            System.out.println(f + ". Loop : " + path);
                    continue;
                }

                if (!forward_ended_indicesi[indexi] && indexj + 1 < nameStations[indexi].size() && !(lightedStation.equals(breakpointStations.get(0)) || lightedStation.equals(breakpointStations.get(1)) || lightedStation.equals(breakpointStations.get(2)) || lightedStation.equals(breakpointStations.get(3)) || lightedStation.equals(breakpointStations.get(4)) || lightedStation.equals(breakpointStations.get(5)) || lightedStation.equals(breakpointStations.get(6)))) {
                    if (path.size() == 1) {
                        indexj++;
                        lightedStation = nameStations[indexi].get(indexj);
                        path.add(lightedStation);
                        //    System.out.println("c");
                        //    System.out.println(f + ". Loop : " + path);
                        continue;
                    } else if (path.size() > 1 && !path.get(path.size() - 2).equals(nameStations[indexi].get(indexj + 1))) {
                        indexj++;
                        lightedStation = nameStations[indexi].get(indexj);
                        path.add(lightedStation);
                        //    System.out.println("c2");
                        //    System.out.println(f + ". Loop : " + path);
                        continue;
                    }

                }

                if (!backward_ended_indicesi[indexi] && indexj - 1 > -1 && !(lightedStation.equals(breakpointStations.get(0)) || lightedStation.equals(breakpointStations.get(1)) || lightedStation.equals(breakpointStations.get(2)) || lightedStation.equals(breakpointStations.get(3)) || lightedStation.equals(breakpointStations.get(4)) || lightedStation.equals(breakpointStations.get(5)) || lightedStation.equals(breakpointStations.get(6)))) {
                    if (path.size() == 1) {
                        indexj--;
                        lightedStation = nameStations[indexi].get(indexj);
                        path.add(lightedStation);
                        //    System.out.println("d");
                        //    System.out.println(f + ". Loop : " + path);
                        continue;
                    } else if (path.size() > 1 && !path.get(path.size() - 2).equals(nameStations[indexi].get(indexj - 1))) {
                        indexj--;
                        lightedStation = nameStations[indexi].get(indexj);
                        path.add(lightedStation);
                        //    System.out.println("d2");
                        //    System.out.println(f + ". Loop : " + path);
                        continue;
                    }


                }

                if (lightedStation.equals(breakpointStations.get(0)) || lightedStation.equals(breakpointStations.get(1)) || lightedStation.equals(breakpointStations.get(2)) || lightedStation.equals(breakpointStations.get(3)) || lightedStation.equals(breakpointStations.get(4)) || lightedStation.equals(breakpointStations.get(5)) || lightedStation.equals(breakpointStations.get(6))) {
                    //    System.out.println("e");

                    int temporaryIndexi = indexi;
                    int temporaryIndexj = indexj;

                    //try out all the possible directions in possible lines
                    for (int r = 0; r < 7; r++) {
                        if (breakpointStations.get(r).equals(lightedStation)) {
                            for (int j = 0; j < breakpointStationsLines[r].size(); j++) {
                                for (int k = 0; k < nameLines.size(); k++) {
                                    if (breakpointStationsLines[r].get(j).equals(nameLines.get(k)) && !backward_ended_indicesi[k]) {
                                        for (int l = 1; l < nameStations[k].size(); l++) {
                                            if (nameStations[k].get(l).equals(lightedStation) && path.size() == 1) {
                                                indexi = k;
                                                indexj = l - 1;
                                                //                    System.out.println("line changed 1");
                                            } else if (nameStations[k].get(l).equals(lightedStation) && !nameStations[k].get(l - 1).equals(path.get(path.size() - 2))) {
                                                indexi = k;
                                                indexj = l - 1;
                                                //                    System.out.println("line changed 2");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int r = 0; r < 7; r++) {
                        if (breakpointStations.get(r).equals(lightedStation)) {
                            for (int j = 0; j < breakpointStationsLines[r].size(); j++) {
                                for (int k = 0; k < nameLines.size(); k++) {
                                    if (breakpointStationsLines[r].get(j).equals(nameLines.get(k)) && !forward_ended_indicesi[k]) {
                                        for (int l = 0; l < nameStations[k].size() - 1; l++) {
                                            if (nameStations[k].get(l).equals(lightedStation) && path.size() == 1) {
                                                indexi = k;
                                                indexj = l + 1;
                                                //                    System.out.println("line changed 3");
                                            } else if (nameStations[k].get(l).equals(lightedStation) && !nameStations[k].get(l + 1).equals(path.get(path.size() - 2))) {
                                                indexi = k;
                                                indexj = l + 1;
                                                //                    System.out.println("line changed 4");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    if (!lightedStation.equals(nameStations[indexi].get(indexj))) {
                        lightedStation = nameStations[indexi].get(indexj);
                        path.add(lightedStation);
                    }

                    else {
                        for (int r = 0; r < 7; r++) {
                            if (breakpointStations.get(r).equals(lightedStation)) {
                                for (int j = 0; j < breakpointStationsLines[r].size(); j++) {
                                    for (int k = 0; k < nameLines.size(); k++) {
                                        if (breakpointStationsLines[r].get(j).equals(nameLines.get(k))) {
                                            for (int l = 1; l < nameStations[k].size(); l++) {
                                                if (nameStations[k].get(l).equals(lightedStation) && nameStations[k].get(l - 1).equals(path.get(path.size() - 2))) {
                                                    indexi = k;
                                                    indexj = l - 1;
                                                    //                        System.out.println("line changed 5");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for (int r = 0; r < 7; r++) {
                            if (breakpointStations.get(r).equals(lightedStation)) {
                                for (int j = 0; j < breakpointStationsLines[r].size(); j++) {
                                    for (int k = 0; k < nameLines.size(); k++) {
                                        if (breakpointStationsLines[r].get(j).equals(nameLines.get(k))) {
                                            for (int l = 0; l < nameStations[k].size() - 1; l++) {
                                                if (nameStations[k].get(l).equals(lightedStation) && nameStations[k].get(l + 1).equals(path.get(path.size() - 2))) {
                                                    indexi = k;
                                                    indexj = l + 1;
                                                    //                        System.out.println("line changed 6");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        lightedStation = nameStations[indexi].get(indexj);
                        path.remove(path.size() - 1);
                        //                System.out.println("one step back");
                    }

                    if (lightedStation.equals(nameStations[temporaryIndexi].get(temporaryIndexj))) { //if no change of labeled station is made, break
                        break;
                    }

                }

                //check if
                else if (!(path.size()==1) && indexj == 0 ) {
                    indexj++;
                    lightedStation = nameStations[indexi].get(indexj);
                    path.remove(path.size() - 1);
                    //                System.out.println("f1");
                }

                else if (!(path.size()==1) && path.get(path.size() - 2).equals(nameStations[indexi].get(indexj - 1))) {
                    indexj--;
                    lightedStation = nameStations[indexi].get(indexj);
                    path.remove(path.size() - 1);
                    //                System.out.println("f2");
                }

                else if (!(path.size()==1) && indexj == nameStations[indexi].size() - 1) {
                    indexj--;
                    lightedStation = nameStations[indexi].get(indexj);
                    path.remove(path.size() - 1);
                    //                 System.out.println("f3");
                }

                else if (!(path.size()==1) && path.get(path.size() - 2).equals(nameStations[indexi].get(indexj + 1))) {
                    indexj++;
                    lightedStation = nameStations[indexi].get(indexj);
                    path.remove(path.size() - 1);
                    //                System.out.println("f4");
                }




                else {
                    System.out.println("These two stations are not connected");
                    break;
                }

                //        System.out.println(f + ". Loop : " + path);

            }

        }
        else{
            System.out.println("The station names provided are not present in this map.");
        }
        if(to.equals(path.get(path.size()-1))){
            StdDraw.enableDoubleBuffering(); // Use for faster animations

            double coordinatex[] = new double[path.size()];
            double coordinatey[] = new double[path.size()];

            for (int st = 0; st < path.size(); st++) {
                for (int x = 0; x < nameLines.size(); x++) {
                    for (int y = 0; y < nameStations[x].size(); y++) {
                        if (nameStations[x].get(y).equals(path.get(st))){
                            coordinatex[st]=xs[x].get(y);
                            coordinatey[st]=ys[x].get(y);
                        }
                    }
                }
            }

            int canvasWidth = 1024;
            int canvasHeight = 482;
            StdDraw.setCanvasSize(canvasWidth, canvasHeight);
            StdDraw.setXscale(0, canvasWidth);
            StdDraw.setYscale(0, canvasHeight);


            for (int x = 0; x < path.size(); x++) {
                StdDraw.picture(512,241,"background.jpg");


                for (int l = 0; l < nameLines.size(); l++) {
                    for (int s = 0; s < nameStations[l].size() - 1; s++) {
                        StdDraw.setPenColor(colors.get(l));
                        StdDraw.setPenRadius(0.012); // set pen size
                        StdDraw.line(xs[l].get(s),ys[l].get(s),xs[l].get(s+1),ys[l].get(s+1));
                    }
                }
                for (int l = 0; l < nameLines.size(); l++) {
                    for (int s = 0; s < nameStations[l].size(); s++) {
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.setFont(new Font("Helvetica", Font.BOLD, 8));
                        if (printOrNots[l].get(s)) {
                            StdDraw.text(xs[l].get(s), ys[l].get(s) + 5, nameStations[l].get(s));
                        }
                        StdDraw.setPenRadius(0.01); // set pen size
                        StdDraw.setPenColor(StdDraw.WHITE);
                        StdDraw.point(xs[l].get(s), ys[l].get(s));

                    }
                }
                for (int s = 0; s < x+1; s++) {
                    StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                    StdDraw.setPenRadius(0.01); // set pen size
                    StdDraw.point(coordinatex[s], coordinatey[s]);
                }
                System.out.println(path.get(x));
                StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
                StdDraw.setPenRadius(0.02); // set pen size
                StdDraw.point(coordinatex[x], coordinatey[x]);
                StdDraw.show();
                StdDraw.pause(300);

            }

        }

    }
}


















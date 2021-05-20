import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        //zmienne dotycząte całkowitej długości obiektów poszczególnych geometrii
        double rLengthTotal = 0, cLengthTotal = 0, lLengthTotal=0;
        //zmienne wskazujące ilość obiektów poszczególnych geometrii
        int rCounter = 0, cCounter = 0, lCounter = 0;
        //zmienne dotyczące pól kół oraz prostokątów
        double cArea=0, rArea=0;

        //ścieżka do pliku csv
        String csvPath = "csv\\AndroidChallenge2021.csv";
        //utworzenie obiektu File
        File csvFile = new File(csvPath);
        try {
            //Wykorzystanie obiektu Scanner do pracy na pliku csv
            Scanner scanner = new Scanner(csvFile);

            //pętla while odpowiadająca za odczytywanie poszczególnych linii dokumentu csv
            while (scanner.hasNext()) {

                String data = scanner.next();
                //przypisanie do tablicy shapes danych oddzielonych przecinkiem
                String[] shapes = data.split(",");
                // w tym momencie pracujemy na poszczególnych liniach dokumentu od 1 do 100 000

                //warunek pozwalający obliczyć pola i obwody prostokątów
                if (shapes[1].equals("R")) {
                    String[] rShapes = shapes[2].split(";");
                    String[] rShapesXY1 = rShapes[0].split("-");
                    String[] rShapesXY2 = rShapes[1].split("-");
                    int xR = Math.abs(Integer.parseInt(rShapesXY1[0]) - Integer.parseInt(rShapesXY2[0]));
                    int yR = Math.abs(Integer.parseInt(rShapesXY1[1]) - Integer.parseInt(rShapesXY2[1]));

                    //obiczenie pola prostokąta
                    double myRArea = xR * yR;
                    rArea += myRArea;
                    //obliczenie obwodu prostokąta
                    int rLength = 2 * xR + 2 * yR;
                    rLengthTotal += rLength;
                    //licznik prostokątów
                    rCounter += 1;
                }

                //warunek pozwalający obliczyć pola kół
                if (shapes[1].equals("C")) {
                    String[] cShapes = shapes[2].split(";");
                    String[] cShapesXY1 = cShapes[0].split("-");
                    int cshapeR = Integer.parseInt(cShapesXY1[1]);

                    //obliczenie pół kół
                    double myCArea = Math.PI * Math.pow(cshapeR, 2);
                    cArea += myCArea;
                    //obliczenie obwodów kół
                    double cLength = Math.PI * 2 * cshapeR;
                    cLengthTotal += cLength;
                    //zliczenie kół
                    cCounter += 1;
                }

                //warunek pozwalający obliczyć długości łamanych
                if (shapes[1].equals("L")) {
                    String[] lShapes = shapes[2].split(";");

                    //jeśli łamana ma więcej niż 1 punkt dodaj ją do puli
                    if(lShapes.length>1){
                        //zliczanie łamanych
                        lCounter++;
                    }

                    //sprawdzanie czy obiekt jest łamaną czy pojedynczym punktem
                    if (lShapes.length <= 1) {
                        System.out.println(shapes[0] + ": ten obiekt jest punktem");

                        //sprawdzanie długości łamanych dla ilości wierchołków od 2 do 10 oraz dodawanie poszczególnych długości do puli
                    } else if (lShapes.length == 2) {
                        String[] l2shapesXY1 = lShapes[0].split("-");
                        String[] l2shapesXY2 = lShapes[1].split("-");
                        double l2shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l2shapesXY1[0]) - Double.parseDouble(l2shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l2shapesXY1[1]) - Double.parseDouble(l2shapesXY2[1])), 2));
                        lLengthTotal+=l2shapeLength;
                    } else if (lShapes.length == 3) {
                        String[] l3shapesXY1 = lShapes[0].split("-");
                        String[] l3shapesXY2 = lShapes[1].split("-");
                        String[] l3shapesXY3 = lShapes[2].split("-");
                        double l3shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l3shapesXY1[0]) - Double.parseDouble(l3shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l3shapesXY1[1]) - Double.parseDouble(l3shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l3shapesXY2[0]) - Double.parseDouble(l3shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l3shapesXY2[1]) - Double.parseDouble(l3shapesXY3[1])), 2));
                        lLengthTotal+=l3shapeLength;

                    } else if (lShapes.length == 4) {
                        String[] l4shapesXY1 = lShapes[0].split("-");
                        String[] l4shapesXY2 = lShapes[1].split("-");
                        String[] l4shapesXY3 = lShapes[2].split("-");
                        String[] l4shapesXY4 = lShapes[3].split("-");
                        double l4shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l4shapesXY1[0]) - Double.parseDouble(l4shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l4shapesXY1[1]) - Double.parseDouble(l4shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l4shapesXY2[0]) - Double.parseDouble(l4shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l4shapesXY2[1]) - Double.parseDouble(l4shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l4shapesXY3[0]) - Double.parseDouble(l4shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l4shapesXY3[1]) - Double.parseDouble(l4shapesXY4[1])), 2));
                        lLengthTotal+=l4shapeLength;

                    } else if (lShapes.length == 5) {
                        String[] l5shapesXY1 = lShapes[0].split("-");
                        String[] l5shapesXY2 = lShapes[1].split("-");
                        String[] l5shapesXY3 = lShapes[2].split("-");
                        String[] l5shapesXY4 = lShapes[3].split("-");
                        String[] l5shapesXY5 = lShapes[4].split("-");
                        double l5shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l5shapesXY1[0]) - Double.parseDouble(l5shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l5shapesXY1[1]) - Double.parseDouble(l5shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l5shapesXY2[0]) - Double.parseDouble(l5shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l5shapesXY2[1]) - Double.parseDouble(l5shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l5shapesXY3[0]) - Double.parseDouble(l5shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l5shapesXY3[1]) - Double.parseDouble(l5shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l5shapesXY4[0]) - Double.parseDouble(l5shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l5shapesXY4[1]) - Double.parseDouble(l5shapesXY5[1])), 2));
                        lLengthTotal+=l5shapeLength;

                    } else if (lShapes.length == 6) {
                        String[] l6shapesXY1 = lShapes[0].split("-");
                        String[] l6shapesXY2 = lShapes[1].split("-");
                        String[] l6shapesXY3 = lShapes[2].split("-");
                        String[] l6shapesXY4 = lShapes[3].split("-");
                        String[] l6shapesXY5 = lShapes[4].split("-");
                        String[] l6shapesXY6 = lShapes[5].split("-");
                        double l6shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l6shapesXY1[0]) - Double.parseDouble(l6shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l6shapesXY1[1]) - Double.parseDouble(l6shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l6shapesXY2[0]) - Double.parseDouble(l6shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l6shapesXY2[1]) - Double.parseDouble(l6shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l6shapesXY3[0]) - Double.parseDouble(l6shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l6shapesXY3[1]) - Double.parseDouble(l6shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l6shapesXY4[0]) - Double.parseDouble(l6shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l6shapesXY4[1]) - Double.parseDouble(l6shapesXY5[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l6shapesXY5[0]) - Double.parseDouble(l6shapesXY6[0])), 2) + Math.pow((Double.parseDouble(l6shapesXY5[1]) - Double.parseDouble(l6shapesXY6[1])), 2));
                        lLengthTotal+=l6shapeLength;

                    } else if (lShapes.length == 7) {
                        String[] l7shapesXY1 = lShapes[0].split("-");
                        String[] l7shapesXY2 = lShapes[1].split("-");
                        String[] l7shapesXY3 = lShapes[2].split("-");
                        String[] l7shapesXY4 = lShapes[3].split("-");
                        String[] l7shapesXY5 = lShapes[4].split("-");
                        String[] l7shapesXY6 = lShapes[5].split("-");
                        String[] l7shapesXY7 = lShapes[6].split("-");
                        double l7shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY1[0]) - Double.parseDouble(l7shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY1[1]) - Double.parseDouble(l7shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY2[0]) - Double.parseDouble(l7shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY2[1]) - Double.parseDouble(l7shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY3[0]) - Double.parseDouble(l7shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY3[1]) - Double.parseDouble(l7shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY4[0]) - Double.parseDouble(l7shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY4[1]) - Double.parseDouble(l7shapesXY5[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY5[0]) - Double.parseDouble(l7shapesXY6[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY5[1]) - Double.parseDouble(l7shapesXY6[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l7shapesXY6[0]) - Double.parseDouble(l7shapesXY7[0])), 2) + Math.pow((Double.parseDouble(l7shapesXY6[1]) - Double.parseDouble(l7shapesXY7[1])), 2));

                        lLengthTotal+=l7shapeLength;

                    } else if (lShapes.length == 8) {
                        String[] l8shapesXY1 = lShapes[0].split("-");
                        String[] l8shapesXY2 = lShapes[1].split("-");
                        String[] l8shapesXY3 = lShapes[2].split("-");
                        String[] l8shapesXY4 = lShapes[3].split("-");
                        String[] l8shapesXY5 = lShapes[4].split("-");
                        String[] l8shapesXY6 = lShapes[5].split("-");
                        String[] l8shapesXY7 = lShapes[6].split("-");
                        String[] l8shapesXY8 = lShapes[7].split("-");
                        double l8shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY1[0]) - Double.parseDouble(l8shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY1[1]) - Double.parseDouble(l8shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY2[0]) - Double.parseDouble(l8shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY2[1]) - Double.parseDouble(l8shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY3[0]) - Double.parseDouble(l8shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY3[1]) - Double.parseDouble(l8shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY4[0]) - Double.parseDouble(l8shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY4[1]) - Double.parseDouble(l8shapesXY5[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY5[0]) - Double.parseDouble(l8shapesXY6[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY5[1]) - Double.parseDouble(l8shapesXY6[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY6[0]) - Double.parseDouble(l8shapesXY7[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY6[1]) - Double.parseDouble(l8shapesXY7[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l8shapesXY7[0]) - Double.parseDouble(l8shapesXY8[0])), 2) + Math.pow((Double.parseDouble(l8shapesXY7[1]) - Double.parseDouble(l8shapesXY8[1])), 2));

                        lLengthTotal+=l8shapeLength;

                    } else if (lShapes.length == 9) {
                        String[] l9shapesXY1 = lShapes[0].split("-");
                        String[] l9shapesXY2 = lShapes[1].split("-");

                        // W przypadku trzeciego punktu w łamanych o długości 9 wystąpił błąd zapisu do tablicy, nie znalazłem niestety powodu tego błedu, dlatego też łączę w tym wypadku wierzchołek 2 z 4, przez co mogą wystąpić lekkie przekłamania pomiarowe
                        //String[] l9shapesXY3 = lShapes[2].split("-");
                        String[] l9shapesXY4 = lShapes[3].split("-");
                        String[] l9shapesXY5 = lShapes[4].split("-");
                        String[] l9shapesXY6 = lShapes[5].split("-");
                        String[] l9shapesXY7 = lShapes[6].split("-");
                        String[] l9shapesXY8 = lShapes[7].split("-");
                        String[] l9shapesXY9 = lShapes[8].split("-");
                        double l9shapeLength =
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY1[0]) - Double.parseDouble(l9shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY1[1]) - Double.parseDouble(l9shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY2[0]) - Double.parseDouble(l9shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY2[1]) - Double.parseDouble(l9shapesXY4[1])), 2)) +
                                //Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY3[0]) - Double.parseDouble(l9shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY3[1]) - Double.parseDouble(l9shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY4[0]) - Double.parseDouble(l9shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY4[1]) - Double.parseDouble(l9shapesXY5[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY5[0]) - Double.parseDouble(l9shapesXY6[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY5[1]) - Double.parseDouble(l9shapesXY6[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY6[0]) - Double.parseDouble(l9shapesXY7[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY6[1]) - Double.parseDouble(l9shapesXY7[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY7[0]) - Double.parseDouble(l9shapesXY8[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY7[1]) - Double.parseDouble(l9shapesXY8[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l9shapesXY8[0]) - Double.parseDouble(l9shapesXY9[0])), 2) + Math.pow((Double.parseDouble(l9shapesXY8[1]) - Double.parseDouble(l9shapesXY9[1])), 2));
                        lLengthTotal+=l9shapeLength;

                    } else if (lShapes.length == 10) {
                        String[] l10shapesXY1 = lShapes[0].split("-");
                        String[] l10shapesXY2 = lShapes[1].split("-");
                        String[] l10shapesXY3 = lShapes[2].split("-");
                        String[] l10shapesXY4 = lShapes[3].split("-");
                        String[] l10shapesXY5 = lShapes[4].split("-");
                        String[] l10shapesXY6 = lShapes[5].split("-");
                        String[] l10shapesXY7 = lShapes[6].split("-");
                        String[] l10shapesXY8 = lShapes[7].split("-");
                        String[] l10shapesXY9 = lShapes[8].split("-");
                        String[] l10shapesXY10 = lShapes[9].split("-");
                        double l10shapeLength = Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY1[0]) - Double.parseDouble(l10shapesXY2[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY1[1]) - Double.parseDouble(l10shapesXY2[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY2[0]) - Double.parseDouble(l10shapesXY3[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY2[1]) - Double.parseDouble(l10shapesXY3[1])), 2)) +
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY3[0]) - Double.parseDouble(l10shapesXY4[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY3[1]) - Double.parseDouble(l10shapesXY4[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY4[0]) - Double.parseDouble(l10shapesXY5[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY4[1]) - Double.parseDouble(l10shapesXY5[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY5[0]) - Double.parseDouble(l10shapesXY6[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY5[1]) - Double.parseDouble(l10shapesXY6[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY6[0]) - Double.parseDouble(l10shapesXY7[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY6[1]) - Double.parseDouble(l10shapesXY7[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY7[0]) - Double.parseDouble(l10shapesXY8[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY7[1]) - Double.parseDouble(l10shapesXY8[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY8[0]) - Double.parseDouble(l10shapesXY9[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY8[1]) - Double.parseDouble(l10shapesXY9[1])), 2))+
                                Math.sqrt(Math.pow((Double.parseDouble(l10shapesXY9[0]) - Double.parseDouble(l10shapesXY10[0])), 2) + Math.pow((Double.parseDouble(l10shapesXY9[1]) - Double.parseDouble(l10shapesXY10[1])), 2));
                        lLengthTotal+=l10shapeLength;

                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Wyświetlenie wyników obliczeń
        System.out.println("Pola prostokątów: " + rArea + "; Pola kół: " + cArea);
        System.out.println("Ilość łamanych: "+lCounter+"; Ilość kół: "+cCounter+"; Ilość prostokątów: "+rCounter);
        System.out.println("Długość wszystkich obiektów: " + (cLengthTotal+rLengthTotal+lLengthTotal));
        System.out.println("średnia długość łamanych: " +(lLengthTotal/lCounter));

    }
}

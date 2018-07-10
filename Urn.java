package com.company;

/*
AlexZaurov
JuliaGillis

Project 1: Treasure Urn
 */

import java.io.*;

/*
Every method writes to a file needs throws IOException.
 */

public class Urn {
    public static final int SIZE = 5;


    public static void main(String[] args) throws IOException {
        // main sets up the file we want to write to:
        FileWriter fw = new FileWriter("urn.txt", false);
        // false means we don't append the file when we re-write the code
        // we overwrite the file when we rewrite the code
        PrintWriter outputFile = new PrintWriter(fw);
        // passes output file to the method drawBase
        drawSectionOne(outputFile);
        drawBaseEdge(outputFile);
        drawSectionTwo(outputFile);
        drawSectionThree(outputFile);
        drawSectionFour(outputFile);
        drawSectionFive(outputFile);
        drawSectionTwo(outputFile);
        drawBase(outputFile);
        outputFile.close();
    }

    //This section draws the lid of the urn
    public static void drawSectionOne(PrintWriter outputFile) throws IOException {

        for (int i = 0; i < (1 + (4 * SIZE)); i++) {
            outputFile.print(' ');
        }
        for (int i = 1; i <= SIZE + SIZE; i++) {
            outputFile.print('=');
        }
        outputFile.print("\n");

        /**
         * The below code controls the amount of lines for the Urn's top going vertical
         */

        for (int line = 1; line <= SIZE; line++) {
            outputFile.print(' ');

            /**
             * The below code controls the spacing in each line
             */


            for (int i = (4*(SIZE-1))-((line -1)*4); i >= 1; i--) {
                outputFile.print(' ');



            }



            for (int i = 0; i < 3; i++) {
                outputFile.print('_');
            }

            outputFile.print('/');
            for (int i = 1; i <= SIZE + ((line - 1) * 4); i++) {
                outputFile.print(':');
            }
            for (int i = 1; i <= SIZE + ((line - 1) * 4); i++) {
                outputFile.print(':');
            }
            outputFile.print('\\');
            for (int i = 1; i <= 3; i++) {
                outputFile.print('_');
            }
            outputFile.print('\n');
        }


    }


    //Sections 2 through 5 contain the main body of the urn


    public static void drawSectionTwo(PrintWriter outputFile) throws IOException {
        for (int line = (SIZE -1); line >= 0; line--) {
            outputFile.print(' ');
            if (line < (SIZE - 1)) {
                for (int i = 1; i < SIZE - line; i++) {
                    outputFile.print(' ');

                }
            }
            outputFile.print('\\');
            for (int i = 1; i <= ((SIZE * 5)-(SIZE-1)) + (line - 1); i++) {
                outputFile.print('{');
            }

            for (int i = 1; i <= ((SIZE * 5)-(SIZE-1)) + (line - 1); i++) {
                outputFile.print('}');
            }
            outputFile.println('/');
        }
    }

    public static void drawSectionThree(PrintWriter outputfile) throws IOException {

        for (int line = 1; line <= (SIZE - 1); line++) {
            outputfile.print(' ');
            if (line < SIZE) {
                for (int i = 0; i < SIZE; i++) {
                    outputfile.print(' ');
                }
            }
            outputfile.print('|');
            for (int i = 1; i <= ((SIZE*4)-1); i++) {
                outputfile.print('(');
            }

            for (int i = 1; i <= ((SIZE*4)-1); i++) {
                outputfile.print(')');
            }
            outputfile.println('|');
        }

    }

    public static void drawSectionFour(PrintWriter outputFile) throws IOException {
        for (int line = 1; line <= SIZE; line++) {
            outputFile.print(' ');
            if (line < SIZE) {
                for (int i = 0; i < (SIZE - line); i++) {
                    outputFile.print(' ');
                }
            }
            outputFile.print('/');
            for (int i = 1; i <= (SIZE*4) + (line - 1); i++) {
                outputFile.print('(');
            }

            for (int i = 1; i <= (SIZE*4) + (line - 1); i++) {
                outputFile.print(')');
            }
            outputFile.println('\\');
        }
    }


    public static void drawSectionFive(PrintWriter outputfile) throws IOException {
        for (int line = 1; line <= SIZE; line++) {
            outputfile.print('|');
            for (int i = 1; i <= 5 * SIZE; i++) {
                outputfile.print('(');
            }

            for (int i = 1; i <= 5 * SIZE; i++) {
                outputfile.print(')');
            }
            outputfile.println('|');
        }

    }

    //This section draws the base of the bottom part of the urn
    public static void drawBase(PrintWriter outputfile) throws IOException {
        drawBaseEdge(outputfile);
        for (int line = 1; line <= ((SIZE*2)-1); line++) {
            outputfile.print('|');
            for (int i = 1; i <= SIZE * 10; i++) {
                outputfile.print(':');
            }
            outputfile.println('|');
        }
        drawBaseEdge(outputfile);
    }


    //This section draws the base edge that is used throughout the design of the urn
    public static void drawBaseEdge(PrintWriter outputfile) throws IOException {
        outputfile.print('+');
        for (int i = 1; i <= (SIZE * 10); i++) {
            outputfile.print('=');
        }
        outputfile.println('+');
    }

}

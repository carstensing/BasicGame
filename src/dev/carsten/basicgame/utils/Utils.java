package dev.carsten.basicgame.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    public static String loadFileAsString(String path) {
        String file = "";
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new FileInputStream(path));

            while(inputStream.hasNextLine()) {
                file += (inputStream.nextLine() + "\n");
            }
            inputStream.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

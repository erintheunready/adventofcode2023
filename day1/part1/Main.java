import java.io.*;
import java.util.ArrayList;

public class Main {
    private static String fileName = "input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("The calibration values are:");
        ArrayList<Integer> calibrationValues = getCalibrationValuesFromFile(fileName);
        int sum = 0;
        for(int i = 0; i < calibrationValues.size(); i++){
            System.out.println(calibrationValues.get(i));
            sum += calibrationValues.get(i);
        }
        System.out.println("The sum is: " + sum);
    }

    private static ArrayList<Integer> getCalibrationValuesFromFile(String inputFile) throws Exception {
        ArrayList<String> scrambledValues = getScrambledValuesFromFile(inputFile);
        ArrayList<Integer> calibrationValues = new ArrayList<Integer>();

        for(int i = 0; i < scrambledValues.size(); i++) {
            String calInts = scrambledValues.get(i).replaceAll("[\\D]", "");
            calibrationValues.add(Integer.parseInt("" + calInts.charAt(0) + calInts.charAt(calInts.length() - 1)));
        }

        return calibrationValues;
    }

    private static ArrayList<String> getScrambledValuesFromFile(String inputFile) throws Exception {
        File file = new File(inputFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> scrambledValues = new ArrayList<String>();

        String st;

        while((st = br.readLine()) != null) {
            scrambledValues.add(st);
        }

        br.close();

        return scrambledValues;
    }
}
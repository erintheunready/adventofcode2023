import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static String fileName = "input.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("The calibration values are:");

        // Create stupid map of stupid strings
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);

        ArrayList<Integer> calibrationValues = getCalibrationValuesFromFile(fileName, numberMap);
        int sum = 0;
        for(int i = 0; i < calibrationValues.size(); i++){
            System.out.println(calibrationValues.get(i));
            sum += calibrationValues.get(i);
        }
        System.out.println("The sum is: " + sum);
    }

    private static ArrayList<Integer> getCalibrationValuesFromFile(String inputFile, Map<String, Integer> numberMap) throws Exception {
        ArrayList<String> scrambledValues = getScrambledValuesFromFile(inputFile);
        ArrayList<Integer> calibrationValues = new ArrayList<Integer>();

        for(int i = 0; i < scrambledValues.size(); i++) {
            calibrationValues.add(Integer.parseInt("" + getFirstDigit(scrambledValues.get(i),numberMap) + getLastDigit(scrambledValues.get(i), numberMap)));
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

    private static int getFirstDigit(String dumbNumber, Map<String, Integer> numberMap) {
        for(int i = 0; i < dumbNumber.length(); i++) {
            String compareString = dumbNumber.substring(i);
            for (Map.Entry<String,Integer> entry : numberMap.entrySet())  {
                String strToMatch = entry.getKey();

                if(compareString.startsWith(strToMatch)){
                    return entry.getValue();
                }
            }
            
            char firstChar = compareString.charAt(0);
            if(Character.isDigit(firstChar)){
                return Integer.parseInt(firstChar + "");
            }
        }

        return 0;
    }

    private static int getLastDigit(String dumbNumber, Map<String, Integer> numberMap) {
        for(int i = 0; i < dumbNumber.length(); i++) {
            String compareString = dumbNumber.substring(0, dumbNumber.length() - i);
            for (Map.Entry<String,Integer> entry : numberMap.entrySet())  {
                String strToMatch = entry.getKey();

                if(compareString.endsWith(strToMatch)){
                    return entry.getValue();
                }
            }
            
            char lastChar = compareString.charAt(compareString.length() - 1);
            if(Character.isDigit(lastChar)){
                return Integer.parseInt(lastChar + "");
            }
        }

        return 0;
    }
}
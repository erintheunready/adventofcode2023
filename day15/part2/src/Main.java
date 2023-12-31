import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = InputParser.getLinesFromLocalFile("input.txt").get(0);

        List<String> instructions = Arrays.asList(input.split(","));
        HashMap<Integer, List<String>> boxes = new HashMap<>();
        HashMap<String, Integer> focalLengths = new HashMap<>();

        for(int i = 0; i < 256; i++){
            boxes.put(i, new ArrayList<>());
        }

        for(String instruction : instructions) {
            String[] splitInst = instruction.split("(=)|(-)");
            String label = splitInst[0];
            int hash = getHash(label);
            if(splitInst.length > 1) {
                int focusLength = Integer.parseInt(splitInst[1]);
                focalLengths.put(label, focusLength);
                if(!boxes.get(hash).contains(label)) {
                    boxes.get(hash).add(label);
                }
                System.out.println(label + "=" + focusLength);
            } else {
                boxes.get(hash).remove(label);
                focalLengths.remove(label);
                System.out.println(label + " -");
            }
        }

        long sum = 0L;

        for (HashMap.Entry<String,Integer> entry : focalLengths.entrySet()){
            sum += getFocusingPower(entry.getKey(), boxes, entry.getValue());
        }

        System.out.println(sum);
    }

    public static int getHash(String e) {
        int hash = 0;
        for(int i = 0; i < e.length(); i++){
            hash += (int) e.charAt(i);
            hash *= 17;
            hash %= 256;
        }
        return hash;
    }

    public static int getFocusingPower(String label, HashMap<Integer, List<String>> boxes, int focalLength) {
        int hash = getHash(label);
        List<String> box = boxes.get(hash);
        return (1 + hash) * (1 + box.indexOf(label)) * focalLength;
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<String> getLinesFromLocalFile(String inputFile)
            throws Exception {
        List<String> fileLines = new ArrayList<>();

        File file = new File(inputFile);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        while ((st = br.readLine()) != null) {
            fileLines.add(st.trim());
        }

        br.close();

        return fileLines;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {

  public static void main(String[] args) throws Exception {
    List<String> fileLines = InputParser.getLinesFromLocalFile("input.txt");

    int lineLength = fileLines.get(0).length();
    int numLines = fileLines.size();
    char[][] charMatrix = new char[lineLength][numLines];
    List<PartNumber> partNumbers = new ArrayList<>();

    for (int i = 0; i < numLines; i++) {
      String currLine = fileLines.get(i);
      String currNumber = "";

      for (int j = 0; j < currLine.length(); j++) {
        char currChar = currLine.charAt(j);
        if (Character.isDigit(currChar)) {
          currNumber = currNumber + currChar;
        } else {
          if (currNumber.length() > 0) {
            partNumbers.add(new PartNumber(Integer.parseInt(currNumber), j, i));
          }
          currNumber = "";
        }
        charMatrix[j][i] = currChar;
      }
      if (currNumber != "") {
        partNumbers.add(
          new PartNumber(Integer.parseInt(currNumber), currLine.length() - 1, i)
        );
      }
    }

    List<Gear> gearList = getGearList(charMatrix);

    int sum = gearList
      .stream()
      .mapToInt(res -> res.getValueIfValid(partNumbers))
      .sum();

    System.out.println("sum: " + sum);
  }

  private static List<Gear> getGearList(char[][] charMatrix){
    List<Gear> gearList = new ArrayList<>();
    for(int j = 0; j < charMatrix[0].length; j++){
      for(int i = 0; i < charMatrix.length; i++){
        if(charMatrix[i][j] == '*'){
          gearList.add(new Gear(i, j));
        }
      }
    }

    return gearList;
  }
}

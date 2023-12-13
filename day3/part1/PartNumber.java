import java.util.ArrayList;
import java.util.List;

class PartNumber {

  private int number;
  private int length;
  private int x;
  private int y;

  public PartNumber(int number, int x, int y) {
    this.number = number;
    this.length = String.valueOf(number).length();
    this.y = y;
    this.x = x - length;
  }

  public int getNumberIfAdjacentToSymbol(char[][] charMatrix) {
    List<Character> adjacentChars = new ArrayList<>();
    // Create list of characters within box
    int startX = x - 1;
    int startY = y - 1;
    int endX = x + length + 1;
    int endY = y + 2;
    if (startX < 0) {
      startX = 0;
    }
    if (startY < 0) {
      startY = 0;
    }
    if (endX >= charMatrix[0].length) {
      endX = charMatrix[0].length;
    }
    if (endY >= charMatrix.length) {
      endY = charMatrix.length;
    }

    for (int j = startY; j < endY; j++) {
      for (int i = startX; i < endX; i++) {
        adjacentChars.add(charMatrix[j][i]);
      }
    }

    for (char a : adjacentChars) {
      if (isSymbol(a)) {
        return number;
      }
    }

    return 0;
  }

  private boolean isSymbol(char currChar) {
    return !(Character.isDigit(currChar) || currChar == '.');
  }
}

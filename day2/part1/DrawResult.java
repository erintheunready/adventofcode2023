public class DrawResult {

  private int red;
  private int blue;
  private int green;

  public DrawResult(String result) {
    String[] splitResult = result.split(",", 3);
    for (String a : splitResult) {
      if (a.endsWith("blue")) {
        blue = getNumFromResultString(a.trim());
      } else if (a.endsWith("green")) {
        green = getNumFromResultString(a.trim());
      } else if (a.endsWith("red")) {
        red = getNumFromResultString(a.trim());
      }
    }
  }

  private static int getNumFromResultString(String ballResult) {
    return Integer.parseInt(ballResult.replaceAll("[\\D]", ""));
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }
}

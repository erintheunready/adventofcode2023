import java.util.List;
import java.util.ArrayList;

public class GameResult {
    private int gameId;
    private List<DrawResult> resultList = new ArrayList<>();
    
    public GameResult (String result) {
        String[] gameIdSplit = result.split(":");
        gameId = Integer.parseInt(gameIdSplit[0].replaceAll("[\\D]", ""));
        String[] splitResult = gameIdSplit[1].split(";");
        for (String a : splitResult){
            DrawResult test = new DrawResult(a);
            resultList.add(test);
        }
    }
    
    public int getIdIfValid(int red, int blue, int green) {
        if(isValidGame(red, blue, green)) {
            return gameId;
        }
        return 0;
    }

    private boolean isValidGame(int red, int blue, int green) {
        for (DrawResult a : resultList) {
            if(a.getRed() > red || a.getBlue() > blue || a.getGreen() > green){
                return false;
            }
        }

        return true;
    }

    public int getPowerOfGame() {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        for (DrawResult a : resultList) {
            if(a.getRed() > minRed) minRed = a.getRed();
            if(a.getBlue() > minBlue) minBlue = a.getBlue();
            if(a.getGreen() > minGreen) minGreen = a.getGreen();
        }

        return minRed * minGreen * minBlue;
    }
}

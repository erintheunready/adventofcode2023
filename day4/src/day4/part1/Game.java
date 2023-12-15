package day4.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<Integer> winningNumbers = new ArrayList<>();
    private final List<Integer> yourNumbers = new ArrayList<>();

    public Game(String gameLine) {
        String gameNumbers = gameLine.split(":")[1].trim();
        String[] gameNumberSplit = gameNumbers.split("\\|");

        String winningNumberStr = gameNumberSplit[0].trim();
        String yourNumberStr = gameNumberSplit[1].trim();

        String[] winningNumberStrArr = winningNumberStr.split(" ");
        for(String a : winningNumberStrArr){
            if(!a.isEmpty()){
                winningNumbers.add(Integer.parseInt((a)));
            }
        }
        String[] yourNumbersStrArr = yourNumberStr.split(" ");
        for(String a : yourNumbersStrArr) {
            if(!a.isEmpty()) {
                yourNumbers.add(Integer.parseInt((a)));
            }
        }
    }

    private List<Integer> getWinningNumbers() {
        List<Integer> wins = new ArrayList<>();
        for(int a : yourNumbers){
            if(winningNumbers.contains((a))){
                wins.add(a);
            }
        }
        return wins;
    }

    public int computeWinSum() {
        List<Integer> wins = getWinningNumbers();
        int exponent = wins.size() - 1;
        if(exponent >= 0){
            return (int) Math.pow(2, exponent);
        }

        return 0;
    }
}

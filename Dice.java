package Dice;

import java.util.HashMap;
import java.util.Map;

public class Dice {
    private static final int N_DICE = 5;
    private static Integer[] dice = new Integer[N_DICE];
    private static Map<Integer, Boolean> tracker = new HashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 1_00; i++){
            firstRoll();
            thriceRoll();
        }
    }



    private static void firstRoll() {
        UtilMethods.diceRoll(dice, N_DICE, tracker);
        UtilMethods.displayDice(dice);
        int count = UtilMethods.yahtzeeOrNot(dice);
        System.out.println("Probability of Yahtzee With One Throw - "+ String.format("%.2f", (float) count/N_DICE*100) + " %");

    }
    private static void thriceRoll() {
        UtilMethods.diceRoll(dice, N_DICE, tracker);
        UtilMethods.displayDice(dice);
        //System.out.println(UtilMethods.highestMultiplicity(dice));
        UtilMethods.nextRoll(dice, N_DICE, tracker);
        int count = UtilMethods.yahtzeeOrNot(dice);
        System.out.println("Probability of Yahtzee With One Throw - "+ String.format("%.2f", (float) count/N_DICE*100) + " %");
    }

}

package Dice;

import java.util.*;

public class UtilMethods {
    private static Random random = new Random();

    public static int highestMultiplicity(Integer[] arr) {
        Arrays.sort(arr);

        int max_count = 1, res = arr[0];
        int curr_count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1])
                curr_count++;
            else
                curr_count = 1;

            if (curr_count > max_count) {
                max_count = curr_count;
                res = arr[i - 1];
            }
        }
        return res;
    }

    public static int yahtzeeOrNot(Integer[] nums) {
        Map<Integer, Integer> count = new HashMap();
        int maxCount = 0;
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            maxCount = Math.max(maxCount, count.get(nums[i]));
        }
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > 1) heap.poll();    // keep only k elements and remove redundant elements
        }
        System.out.println(heap.peek() +" "+maxCount);
        return heap.peek();
    }
    static void diceRoll(Integer[] dice, int N_DICE, Map<Integer, Boolean> tracker) {
        for (int i = 0; i < N_DICE; i++) {
            dice[i] = 1 + random.nextInt(6);
            tracker.put(dice[i], true);
        }
    }
    static void displayDice(Integer dice[]){
        System.out.println(Arrays.toString(dice));
    }

    /** Overloaded method to change the value of a single selected die. */
    static void rollDice(int die, Integer dice[], Map<Integer, Boolean> tracker) {
        dice[die] = 1 + random.nextInt(6);
        tracker.put(die, false);
    }

    /**
     * Prompts player to select the desired dice to reroll.
     * Assigns new values to selected die.
     */
    static void nextRoll(Integer[] dice, int N_DICE, Map<Integer, Boolean> tracker) {
        for (int i = 0; i < 2; i++) {
            int flag = yahtzeeOrNot(dice);
            tracker.put(flag, false);
            System.out.println(tracker);
            System.out.println("Select dice to reroll.");
            for (int j = 0; j < N_DICE; j++) {
                if(tracker.get(dice[j]) == true){
                    rollDice(j, dice, tracker);
                }
            }
            displayDice(dice);
        }
    }
}

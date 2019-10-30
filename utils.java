package exchangetask;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class utils {

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public static ArrayList<Long> getUniqueRandomNumberInRange(int n, Long k) {

        Random random = new Random();
        if (n > k) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        ArrayList<Long> arrayList = new ArrayList<Long>();

        while (arrayList.size() < n) { // how many numbers u need - it will 6
            // int a = random.nextLong(k) + 1; // this will give numbers between 1 and 50.
            Long a = Math.abs(random.nextLong());

            if (!arrayList.contains(a)) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    public static int givenList_shouldReturnARandomElement() {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

    public static void main(String[] args) {

        ArrayList<Long> rand = utils.getUniqueRandomNumberInRange(5, 5L);

        for (Long num : rand) {
            System.out.println(num);
        }
    }

}

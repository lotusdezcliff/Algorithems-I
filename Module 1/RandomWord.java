import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String champion =  null;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (StdRandom.uniform(i) == 0) {
                champion = word;
            }
            i++;
        }
        System.out.println(champion);
    }
}

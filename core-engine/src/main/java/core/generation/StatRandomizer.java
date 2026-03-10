package core.generation;

import java.util.concurrent.ThreadLocalRandom;

public class StatRandomizer {

    public static int randomizeHP(int finalHp) {
        double variance = ThreadLocalRandom.current().nextDouble(0.95, 1.05);
        return (int) Math.round(finalHp * variance);
    }
}
package core.generation;

import java.util.concurrent.ThreadLocalRandom;

public class StatRandomizer {

    /**
     * Applies a controlled ±5% randomization to large numerical statistics (like HP)
     * to ensure organic variety between encounters of the same creature type.
     */
    public static int randomizeHP(int finalHp) {
        double variance = ThreadLocalRandom.current().nextDouble(0.95, 1.05);
        return (int) Math.round(finalHp * variance);
    }
}
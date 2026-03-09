package core.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import core.domain.MonsterFamily;

public class AbilitySelector {

    /**
     * Randomly selects 2 to 4 thematically appropriate abilities based on the creature's level tier.
     */
    public static List<String> selectAbilities(MonsterFamily family, int level) {
        MonsterFamily.AbilityPool pool = family.abilityPool();
        List<String> availableAbilities = new ArrayList<>();

        // Tier 1: Levels -1 to 4 (Low-level creatures)
        if (level <= 4) {
            availableAbilities.addAll(pool.lowLevelAbilities());
        } 
        // Tier 2: Levels 5 to 14 (Mid-level creatures)
        else if (level <= 14) {
            availableAbilities.addAll(pool.lowLevelAbilities());
            availableAbilities.addAll(pool.midLevelAbilities());
        } 
        // Tier 3: Levels 15 to 25 (High-level boss creatures)
        else {
            availableAbilities.addAll(pool.midLevelAbilities());
            availableAbilities.addAll(pool.highLevelAbilities());
        }

        if (availableAbilities.isEmpty()) {
            return new ArrayList<>();
        }

        // Shuffle the available abilities to randomize the selection
        Collections.shuffle(availableAbilities);

        // Determine how many abilities to grant (between 2 and 4 inclusive)
        int numAbilities = ThreadLocalRandom.current().nextInt(2, 5);

        int limit = Math.min(numAbilities, availableAbilities.size());
        return new ArrayList<>(availableAbilities.subList(0, limit));
    }
}
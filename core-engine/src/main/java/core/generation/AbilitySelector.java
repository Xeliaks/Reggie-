package core.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import core.domain.MonsterFamily;

public class AbilitySelector {

   
    public static List<String> selectAbilities(MonsterFamily family, int level) {
        MonsterFamily.AbilityPool pool = family.abilityPool();
        List<String> availableAbilities = new ArrayList<>();

        
        if (level <= 4) {
            availableAbilities.addAll(pool.lowLevelAbilities());
        } 
       
        else if (level <= 14) {
            availableAbilities.addAll(pool.lowLevelAbilities());
            availableAbilities.addAll(pool.midLevelAbilities());
        } 
      
        else {
            availableAbilities.addAll(pool.midLevelAbilities());
            availableAbilities.addAll(pool.highLevelAbilities());
        }

        if (availableAbilities.isEmpty()) {
            return new ArrayList<>();
        }

     
        Collections.shuffle(availableAbilities);

        
        int numAbilities = ThreadLocalRandom.current().nextInt(2, 5);

        int limit = Math.min(numAbilities, availableAbilities.size());
        return new ArrayList<>(availableAbilities.subList(0, limit));
    }
}
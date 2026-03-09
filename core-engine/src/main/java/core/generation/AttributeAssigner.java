package core.generation;

import java.util.HashMap;
import java.util.Map;

import core.domain.MonsterFamily;
import core.domain.MonsterFamily.AbilityScore;

public class AttributeAssigner {

    
     // Distributes ability modifiers based on the family priorities and level.
     
    public static Map<AbilityScore, Integer> assignAttributes(MonsterFamily family, int level) {
        Map<AbilityScore, Integer> attributes = new HashMap<>();

        // Calculate scaling modifiers based on level math.
        int primaryMod = Math.max(0, (level / 2) + 2);
        int secondaryMod = Math.max(0, (level / 3) + 1);
        int tertiaryMod = Math.max(0, (level / 4));

        // Initialize all to a standard +0 baseline
        for (AbilityScore score : AbilityScore.values()) {
            attributes.put(score, 0);
        }

        // Apply the family's biological priorities to the highest stats
        var priorities = family.abilityPriorities();
        if (priorities.size() > 0) attributes.put(priorities.get(0), primaryMod);
        if (priorities.size() > 1) attributes.put(priorities.get(1), secondaryMod);
        if (priorities.size() > 2) attributes.put(priorities.get(2), tertiaryMod);

        return attributes;
    }
}
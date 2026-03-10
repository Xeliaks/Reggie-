package core.generation;

import java.util.HashMap;
import java.util.Map;

import core.domain.MonsterFamily;
import core.domain.MonsterFamily.AbilityScore;

public class AttributeAssigner {

    

     
    public static Map<AbilityScore, Integer> assignAttributes(MonsterFamily family, int level) {
        Map<AbilityScore, Integer> attributes = new HashMap<>();

     
        int primaryMod = Math.max(0, (level / 2) + 2);
        int secondaryMod = Math.max(0, (level / 3) + 1);
        int tertiaryMod = Math.max(0, (level / 4));

      
        for (AbilityScore score : AbilityScore.values()) {
            attributes.put(score, 0);
        }


        var priorities = family.abilityPriorities();
        if (priorities.size() > 0) attributes.put(priorities.get(0), primaryMod);
        if (priorities.size() > 1) attributes.put(priorities.get(1), secondaryMod);
        if (priorities.size() > 2) attributes.put(priorities.get(2), tertiaryMod);

        return attributes;
    }
}
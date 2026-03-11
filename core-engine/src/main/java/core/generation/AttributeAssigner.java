package core.generation;

import java.util.HashMap;
import java.util.Map;

import core.domain.Archetype;
import core.domain.MonsterFamily;
import core.domain.MonsterFamily.AbilityScore;

public class AttributeAssigner {

    public static Map<AbilityScore, Integer> assignAttributes(MonsterFamily family, Archetype archetype, int level) {

        System.out.println("--> [DEBUG] Running NEW Attribute Assigner for Archetype: " + archetype.roleName());

        Map<AbilityScore, Integer> attributes = new HashMap<>();

        int high = Math.min(10, 3 + (level / 3));
        int moderate = Math.min(7, 1 + (level / 4));
        int low = Math.max(-3, -1 + (level / 5));

        String role = archetype.roleName().toLowerCase();


        switch (role) {
            case "brute":
                attributes.put(AbilityScore.STRENGTH, high);
                attributes.put(AbilityScore.CONSTITUTION, high);
                attributes.put(AbilityScore.DEXTERITY, moderate);
                attributes.put(AbilityScore.INTELLIGENCE, low);
                attributes.put(AbilityScore.WISDOM, low);
                attributes.put(AbilityScore.CHARISMA, low);
                break;

            case "skirmisher":
                attributes.put(AbilityScore.DEXTERITY, high);
                attributes.put(AbilityScore.STRENGTH, moderate);
                attributes.put(AbilityScore.CONSTITUTION, moderate);
                attributes.put(AbilityScore.WISDOM, moderate);
                attributes.put(AbilityScore.INTELLIGENCE, low);
                attributes.put(AbilityScore.CHARISMA, low);
                break;

            case "spellcaster":
                attributes.put(AbilityScore.INTELLIGENCE, high);
                attributes.put(AbilityScore.DEXTERITY, moderate);
                attributes.put(AbilityScore.WISDOM, moderate);
                attributes.put(AbilityScore.CHARISMA, moderate);
                attributes.put(AbilityScore.STRENGTH, low);
                attributes.put(AbilityScore.CONSTITUTION, low);
                break;

            default:
                for (AbilityScore score : AbilityScore.values()) {
                    attributes.put(score, low);
                }
                var priorities = family.abilityPriorities();
                if (priorities.size() > 0) attributes.put(priorities.get(0), high);
                if (priorities.size() > 1) attributes.put(priorities.get(1), moderate);
                if (priorities.size() > 2) attributes.put(priorities.get(2), moderate);
                break;
        }

        return attributes;
    }
}
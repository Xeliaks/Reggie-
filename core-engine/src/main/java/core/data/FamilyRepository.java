package core.data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import core.domain.MonsterFamily;

public class FamilyRepository {

    
    public static List<MonsterFamily> loadAllFamilies() {
        return List.of(
            getUndeadFamily(),
            getAnimalFamily(),
            getHumanoidFamily()
        );
    }

    private static MonsterFamily getUndeadFamily() {
        return new MonsterFamily(
            "Undead",
            List.of(
                MonsterFamily.AbilityScore.STRENGTH, 
                MonsterFamily.AbilityScore.CHARISMA, 
                MonsterFamily.AbilityScore.CONSTITUTION
            ),
            MonsterFamily.StatScale.MODERATE, // AC
            MonsterFamily.StatScale.HIGH,     // Fortitude
            MonsterFamily.StatScale.LOW,      // Reflex
            MonsterFamily.StatScale.MODERATE, // Will
            Set.of(MonsterFamily.MovementType.TERRESTRIAL),
            Set.of(MonsterFamily.Sense.DARKVISION),
            new MonsterFamily.DefensiveTraits(
                Set.of("death effects", "disease", "paralyzed", "poison", "mindless"), 
                Collections.emptySet(), 
                Set.of("vitality") 
            ),
            Set.of("Necril", "Common"),
            new MonsterFamily.AbilityPool(
                List.of("Grab", "Putrid Stench"),               
                List.of("Life Drain", "Frightful Presence"),    
                List.of("Devour Soul", "Create Spawn")          
            )
        );
    }

    private static MonsterFamily getAnimalFamily() {
        return new MonsterFamily(
            "Animal",
            List.of(
                MonsterFamily.AbilityScore.STRENGTH, 
                MonsterFamily.AbilityScore.DEXTERITY, 
                MonsterFamily.AbilityScore.CONSTITUTION
            ),
            MonsterFamily.StatScale.MODERATE, // AC
            MonsterFamily.StatScale.HIGH,     // Fortitude
            MonsterFamily.StatScale.HIGH,     // Reflex
            MonsterFamily.StatScale.LOW,      // Will
            Set.of(MonsterFamily.MovementType.TERRESTRIAL),
            Set.of(MonsterFamily.Sense.LOW_LIGHT_VISION, MonsterFamily.Sense.SCENT),
            new MonsterFamily.DefensiveTraits(
                Collections.emptySet(), 
                Collections.emptySet(), 
                Collections.emptySet()
            ),
            Collections.emptySet(), 
            new MonsterFamily.AbilityPool(
                List.of("Knockdown", "Pack Attack"),               
                List.of("Pounce", "Rend"),    
                List.of("Trample", "Swallow Whole")          
            )
        );
    }

    private static MonsterFamily getHumanoidFamily() {
        return new MonsterFamily(
            "Humanoid",
            List.of(
                MonsterFamily.AbilityScore.DEXTERITY, 
                MonsterFamily.AbilityScore.INTELLIGENCE, 
                MonsterFamily.AbilityScore.CONSTITUTION
            ),
            MonsterFamily.StatScale.MODERATE, // AC
            MonsterFamily.StatScale.MODERATE, // Fortitude
            MonsterFamily.StatScale.MODERATE, // Reflex
            MonsterFamily.StatScale.MODERATE, // Will
            Set.of(MonsterFamily.MovementType.TERRESTRIAL),
            Collections.emptySet(), 
            new MonsterFamily.DefensiveTraits(
                Collections.emptySet(), 
                Collections.emptySet(), 
                Collections.emptySet()
            ),
            Set.of("Common", "Regional"),
            new MonsterFamily.AbilityPool(
                List.of("Shield Block", "Sneak Attack"),               
                List.of("Reactive Strike", "Command"),    
                List.of("Whirlwind Strike", "Master Strike")          
            )
        );
    }
}

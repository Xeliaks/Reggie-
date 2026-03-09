package core.domain;

import java.util.List;
import java.util.Set;

public record MonsterFamily(
    String familyName,
    
    
    List<AbilityScore> abilityPriorities,
    
    
    StatScale acScale,
    StatScale fortitudeScale,
    StatScale reflexScale,
    StatScale willScale,
    
    
    Set<MovementType> commonMovementTypes,
    Set<Sense> defaultSenses,
    
    
    DefensiveTraits defaultDefenses,
    Set<String> commonLanguages,
    
     
    AbilityPool abilityPool
) {
    
    public enum AbilityScore {
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA
    }

    
    public enum StatScale {
        EXTREME, HIGH, MODERATE, LOW, TERRIBLE
    }

    public enum MovementType {
        TERRESTRIAL, AQUATIC, FLYING, BURROWING, CLIMBING
    }

    public enum Sense {
        DARKVISION, LOW_LIGHT_VISION, TREMORSENSE, LIFESENSE, SCENT, ECHOLOCATION
    }

    
    public record DefensiveTraits(
        Set<String> immunities,
        Set<String> resistances,
        Set<String> weaknesses
    ) {}

    public record AbilityPool(
        List<String> lowLevelAbilities,
        List<String> midLevelAbilities,
        List<String> highLevelAbilities
    ) {}
}
package core.domain;

import java.util.List;

public record Archetype(
    String roleName,
    
    // Health Modifiers
    double hpMultiplier,        
    int flatHpPerLevelModifier, 

    // Offense Modifiers
    double damageMultiplier,   
    int attackBonusModifier,    
    
    // Defense Modifiers 
    int acModifier,
    int fortitudeModifier,
    int reflexModifier,
    int willModifier,
    
    // Utility Modifiers
    int speedModifier,          
    int perceptionModifier,
    
    // Abilities
    List<String> grantedAbilities
) {
    
    public static Archetype createBasic(String name) {
        return new Archetype(
            name, 1.0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, List.of()
        );
    }
}
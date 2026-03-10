package core.domain;

import java.util.List;

public record Archetype(
    String roleName,
   
    double hpMultiplier,        
    int flatHpPerLevelModifier, 

   
    double damageMultiplier,   
    int attackBonusModifier,    
    
   
    int acModifier,
    int fortitudeModifier,
    int reflexModifier,
    int willModifier,
    
  
    int speedModifier,          
    int perceptionModifier,
    
   
    List<String> grantedAbilities
) {
    
    public static Archetype createBasic(String name) {
        return new Archetype(
            name, 1.0, 0, 1.0, 0, 0, 0, 0, 0, 0, 0, List.of()
        );
    }
}
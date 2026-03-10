package core.generation;

import core.domain.Archetype;

public class ArchetypeApplier {

 
    public static int applyHP(int baseHp, int level, Archetype archetype) {
        int effectiveLevel = Math.max(1, level); 
        
        double multipliedHp = baseHp * archetype.hpMultiplier();
        int flatModifier = effectiveLevel * archetype.flatHpPerLevelModifier();
        
        return (int) Math.round(multipliedHp) + flatModifier;
    }

  
    public static int applyAC(int baseAc, Archetype archetype) {
        return baseAc + archetype.acModifier();
    }

 
    public static int applyFortitude(int baseFort, Archetype archetype) {
        return baseFort + archetype.fortitudeModifier();
    }

   
    public static int applyReflex(int baseReflex, Archetype archetype) {
        return baseReflex + archetype.reflexModifier();
    }

    
    public static int applyWill(int baseWill, Archetype archetype) {
        return baseWill + archetype.willModifier();
    }

    
    public static int applyStrikeBonus(int baseStrikeBonus, Archetype archetype) {
        return baseStrikeBonus + archetype.attackBonusModifier();
    }
}
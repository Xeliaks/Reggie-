package core.generation;

import core.domain.Archetype;

public class ArchetypeApplier {

    /**
     * Applies HP multipliers and per-level flat modifiers.
     */
    public static int applyHP(int baseHp, int level, Archetype archetype) {
        int effectiveLevel = Math.max(1, level); 
        
        double multipliedHp = baseHp * archetype.hpMultiplier();
        int flatModifier = effectiveLevel * archetype.flatHpPerLevelModifier();
        
        return (int) Math.round(multipliedHp) + flatModifier;
    }

    /**
     * Applies the flat AC modifier.
     */
    public static int applyAC(int baseAc, Archetype archetype) {
        return baseAc + archetype.acModifier();
    }

    /**
     * Applies the flat Fortitude Save modifier.
     */
    public static int applyFortitude(int baseFort, Archetype archetype) {
        return baseFort + archetype.fortitudeModifier();
    }

    /**
     * Applies the flat Reflex Save modifier.
     */
    public static int applyReflex(int baseReflex, Archetype archetype) {
        return baseReflex + archetype.reflexModifier();
    }

    /**
     * Applies the flat Will Save modifier.
     */
    public static int applyWill(int baseWill, Archetype archetype) {
        return baseWill + archetype.willModifier();
    }

    /**
     * Applies the Strike Bonus modifier (e.g., Archers get higher attack bonuses).
     */
    public static int applyStrikeBonus(int baseStrikeBonus, Archetype archetype) {
        return baseStrikeBonus + archetype.attackBonusModifier();
    }
}
package core.generation;

import core.domain.MonsterFamily.StatScale;

public class BaseStatCalculator {

    /**
     * Calculates baseline Armor Class.
     * Standard AC in PF2e scales at roughly 15 + Level.
     */
    public static int calculateAC(int level, StatScale scale) {
        int baseAc = 15 + level;
        return switch (scale) {
            case EXTREME -> baseAc + 3;
            case HIGH -> baseAc + 1;
            case MODERATE -> baseAc;
            case LOW -> baseAc - 2;
            case TERRIBLE -> baseAc - 4;
        };
    }

    /**
     * Calculates baseline Saving Throws (Fortitude, Reflex, Will).
     * Standard saves scale at roughly 5 + Level.
     */
    public static int calculateSave(int level, StatScale scale) {
        int baseSave = 5 + level;
        return switch (scale) {
            case EXTREME -> baseSave + 3;
            case HIGH -> baseSave + 1;
            case MODERATE -> baseSave;
            case LOW -> baseSave - 2;
            case TERRIBLE -> baseSave - 4;
        };
    }

    /**
     * Calculates baseline Hit Points.
     * HP scales dramatically per level based on creature role.
     */
    public static int calculateHP(int level, StatScale scale) {
        int effectiveLevel = Math.max(1, level); 
        int baseHp = effectiveLevel * 15;
        
        return switch (scale) {
            case EXTREME -> (int)(baseHp * 1.5);
            case HIGH -> (int)(baseHp * 1.2);
            case MODERATE -> baseHp;
            case LOW -> (int)(baseHp * 0.8);
            case TERRIBLE -> (int)(baseHp * 0.5);
        };
    }

    /**
     * Calculates baseline Strike Bonus (Attack Roll).
     */
    public static int calculateStrikeBonus(int level, StatScale scale) {
        int baseStrike = 7 + level;
        return switch (scale) {
            case EXTREME -> baseStrike + 3;
            case HIGH -> baseStrike + 1;
            case MODERATE -> baseStrike;
            case LOW -> baseStrike - 2;
            case TERRIBLE -> baseStrike - 4;
        };
    }
}
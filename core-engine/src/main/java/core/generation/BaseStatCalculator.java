package core.generation;

import core.domain.MonsterFamily.StatScale;

public class BaseStatCalculator {

  
    private static int getScaleOffset(StatScale scale) {
        return switch (scale) {
            case EXTREME  ->  3;
            case HIGH     ->  1;
            case MODERATE ->  0;
            case LOW      -> -2;
            case TERRIBLE -> -4;
        };
    }

    public static int calculateAC(int level, StatScale scale) {

        int baseAc = 15 + level + (level / 4);
        return baseAc + getScaleOffset(scale);
    }

    public static int calculateSave(int level, StatScale scale) {
        int baseSave = 5 + level + (level / 5);
        return baseSave + getScaleOffset(scale);
    }

    public static int calculateStrikeBonus(int level, StatScale scale) {
        int baseStrike = 7 + level + (level / 4);
        return baseStrike + getScaleOffset(scale);
    }

    public static int calculateHP(int level, StatScale scale) {
        int effectiveLevel = Math.max(1, level); 
        int baseHp = 15 + (effectiveLevel * 12);
        
        return switch (scale) {
            case EXTREME  -> (int)(baseHp * 1.5);
            case HIGH     -> (int)(baseHp * 1.2);
            case MODERATE -> baseHp;
            case LOW      -> (int)(baseHp * 0.8);
            case TERRIBLE -> (int)(baseHp * 0.5);
        };
    }
}
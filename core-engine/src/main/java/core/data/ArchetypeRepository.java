package core.data;

import java.util.List;

import core.domain.Archetype;

public class ArchetypeRepository {

    public static List<Archetype> loadAllArchetypes() {
        return List.of(
            getBrute(),
            getSkirmisher(),
            getSpellcaster()
        );
    }

    private static Archetype getBrute() {
        // Brute: +20% HP, +10% damage, -2 AC, -1 Reflex, +2 Fortitude 
        return new Archetype(
            "Brute",
            1.20,  // hpMultiplier
            0,     // flatHpPerLevelModifier
            1.10,  // damageMultiplier
            0,     // attackBonusModifier
            -2,    // acModifier
            +2,    // fortitudeModifier
            -1,    // reflexModifier
            0,     // willModifier
            0,     // speedModifier
            0,     // perceptionModifier
            List.of("Push", "Knockback") // Granted abilities
        );
    }

    private static Archetype getSkirmisher() {
        // Skirmisher: +2 AC, +10 Speed, +1 Reflex, -10% HP 
        return new Archetype(
            "Skirmisher",
            0.90,  // hpMultiplier
            0,     // flatHpPerLevelModifier
            1.00,  // damageMultiplier
            0,     // attackBonusModifier
            +2,    // acModifier
            0,     // fortitudeModifier
            +1,    // reflexModifier
            0,     // willModifier
            +10,   // speedModifier
            0,     // perceptionModifier
            List.of("Mobility", "Hit and Run") // Granted abilities
        );
    }

    private static Archetype getSpellcaster() {
        // Spellcaster: Adds spell list appropriate to tradition, -2 HP per level, +2 Will, +2 to mental ability score 
        return new Archetype(
            "Spellcaster",
            1.00,  // hpMultiplier
            -2,    // flatHpPerLevelModifier
            1.00,  // damageMultiplier
            0,     // attackBonusModifier
            0,     // acModifier
            0,     // fortitudeModifier
            0,     // reflexModifier
            +2,    // willModifier
            0,     // speedModifier
            0,     // perceptionModifier
            List.of("Innate Spells", "Magical Tradition") // Granted abilities
        );
    }
}
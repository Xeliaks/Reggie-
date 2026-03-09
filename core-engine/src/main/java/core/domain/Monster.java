package core.domain; // Matching your current package structure

import java.util.List;
import java.util.Set;

public record Monster(
    // Identity 
    String name,
    int level,
    String familyName,
    String archetypeName,

    // Core Attributes 
    int strength,
    int dexterity,
    int constitution,
    int intelligence,
    int wisdom,
    int charisma,

    // Defenses 
    int armorClass,
    int hitPoints,
    int fortitudeSave,
    int reflexSave,
    int willSave,
    Set<String> immunities,
    Set<String> resistances,
    Set<String> weaknesses,

    // Senses and Communication
    int perception,
    Set<String> senses,
    Set<String> languages,

    // Movement 
    int speed,
    Set<String> movementTypes,

    // Offense & Abilities 
    int strikeBonus,
    String strikeDamage,
    List<String> specialAbilities
) {}
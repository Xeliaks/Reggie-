package core.domain; // Matching your current package structure

import java.util.List;
import java.util.Set;

public record Monster(
 
    String name,
    int level,
    String familyName,
    String archetypeName,

 
    int strength,
    int dexterity,
    int constitution,
    int intelligence,
    int wisdom,
    int charisma,


    int armorClass,
    int hitPoints,
    int fortitudeSave,
    int reflexSave,
    int willSave,
    Set<String> immunities,
    Set<String> resistances,
    Set<String> weaknesses,

   
    int perception,
    Set<String> senses,
    Set<String> languages,

   
    int speed,
    Set<String> movementTypes,

   
    int strikeBonus,
    String strikeDamage,
    List<String> specialAbilities
) {}
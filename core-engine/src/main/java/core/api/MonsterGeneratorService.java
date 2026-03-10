package core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import core.domain.Archetype;
import core.domain.Monster;
import core.domain.MonsterFamily;
import core.domain.MonsterFamily.AbilityScore;
import core.domain.MonsterFamily.StatScale;
import core.generation.AbilitySelector;
import core.generation.ArchetypeApplier;
import core.generation.AttributeAssigner;
import core.generation.BaseStatCalculator;
import core.generation.NameGenerator;
import core.generation.StatRandomizer;

public class MonsterGeneratorService {


    public Monster generateMonster(MonsterFamily family, Archetype archetype, int level) {
        
        if (family == null) {
            throw new IllegalArgumentException("Security Exception: MonsterFamily cannot be null.");
        }
        if (archetype == null) {
            throw new IllegalArgumentException("Security Exception: Archetype cannot be null.");
        }
        if (level < -1 || level > 25) {
            throw new IllegalArgumentException("Security Exception: Level must be strictly between -1 and 25. Received: " + level);
        }
       
        int baseAc = BaseStatCalculator.calculateAC(level, family.acScale());
        int baseFort = BaseStatCalculator.calculateSave(level, family.fortitudeScale());
        int baseReflex = BaseStatCalculator.calculateSave(level, family.reflexScale());
        int baseWill = BaseStatCalculator.calculateSave(level, family.willScale());
        
        int baseHp = BaseStatCalculator.calculateHP(level, StatScale.MODERATE); 
        int baseStrike = BaseStatCalculator.calculateStrikeBonus(level, StatScale.MODERATE);

     
        int archAc = ArchetypeApplier.applyAC(baseAc, archetype);
        int archFort = ArchetypeApplier.applyFortitude(baseFort, archetype);
        int archReflex = ArchetypeApplier.applyReflex(baseReflex, archetype);
        int archWill = ArchetypeApplier.applyWill(baseWill, archetype);
        int archHp = ArchetypeApplier.applyHP(baseHp, level, archetype);
        int finalStrike = ArchetypeApplier.applyStrikeBonus(baseStrike, archetype);

        
    
       
        int finalHp = StatRandomizer.randomizeHP(archHp);
        List<String> selectedAbilities = AbilitySelector.selectAbilities(family, level);
        
      
        List<String> finalAbilities = new ArrayList<>(selectedAbilities);
        finalAbilities.addAll(archetype.grantedAbilities());

     
        Map<AbilityScore, Integer> attributes = AttributeAssigner.assignAttributes(family, level);

      
      
        String generatedName = NameGenerator.generateRandomName();
        
      
        int finalPerception = (5 + level) + attributes.get(AbilityScore.WISDOM) + archetype.perceptionModifier();
        int finalSpeed = 25 + archetype.speedModifier();
        
    
        int damageDice = Math.max(1, (level / 4) + 1);
        String finalDamage = damageDice + "d8 + " + attributes.get(AbilityScore.STRENGTH) + " physical";

       
        Set<String> movementStrings = family.commonMovementTypes().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
                
        Set<String> sensesStrings = family.defaultSenses().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        return new Monster(
            generatedName,
            level,
            family.familyName(),
            archetype.roleName(),
            attributes.get(AbilityScore.STRENGTH),
            attributes.get(AbilityScore.DEXTERITY),
            attributes.get(AbilityScore.CONSTITUTION),
            attributes.get(AbilityScore.INTELLIGENCE),
            attributes.get(AbilityScore.WISDOM),
            attributes.get(AbilityScore.CHARISMA),
            archAc,
            finalHp,
            archFort,
            archReflex,
            archWill,
            family.defaultDefenses().immunities(),
            family.defaultDefenses().resistances(),
            family.defaultDefenses().weaknesses(),
            finalPerception,
            sensesStrings,
            family.commonLanguages(),
            finalSpeed,
            movementStrings,
            finalStrike,
            finalDamage,
            finalAbilities
        );
    }
}
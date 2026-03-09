package core.api; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import core.data.ArchetypeRepository;
import core.data.FamilyRepository;
import core.domain.Archetype;
import core.domain.Monster;
import core.domain.MonsterFamily;

public class MonsterGeneratorServiceTest {

    @Test
    public void testGenerateLevel7UndeadBrute() {
        MonsterFamily undeadFamily = FamilyRepository.loadAllFamilies().stream()
                .filter(f -> f.familyName().equals("Undead"))
                .findFirst()
                .orElseThrow();
                
        Archetype bruteArchetype = ArchetypeRepository.loadAllArchetypes().stream()
                .filter(a -> a.roleName().equals("Brute"))
                .findFirst()
                .orElseThrow();

        MonsterGeneratorService generator = new MonsterGeneratorService();

        int targetLevel = 7;
        Monster generatedMonster = generator.generateMonster(undeadFamily, bruteArchetype, targetLevel);

        System.out.println("=========================================");
        System.out.println("           ENCOUNTER GENERATED           ");
        System.out.println("=========================================");
        System.out.println("Name: " + generatedMonster.name());
        System.out.println("Level: " + generatedMonster.level());
        System.out.println("HP: " + generatedMonster.hitPoints());
        System.out.println("AC: " + generatedMonster.armorClass());
        System.out.println("Fortitude: +" + generatedMonster.fortitudeSave());
        System.out.println("Reflex: +" + generatedMonster.reflexSave());
        System.out.println("Will: +" + generatedMonster.willSave());
        System.out.println("Speed: " + generatedMonster.speed() + " ft");
        System.out.println("Damage: " + generatedMonster.strikeDamage());
        System.out.println("Abilities: " + generatedMonster.specialAbilities());
        System.out.println("Immunities: " + generatedMonster.immunities());
        System.out.println("=========================================");

        assertNotNull(generatedMonster);
        assertEquals("Level 7 Undead Brute", generatedMonster.name());
        assertEquals(7, generatedMonster.level());
    }
}
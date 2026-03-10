package core.api; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.data.ArchetypeRepository;
import core.data.FamilyRepository;
import core.domain.Archetype;
import core.domain.Monster;
import core.domain.MonsterFamily;

public class MonsterGeneratorServiceTest {

    private MonsterGeneratorService generator;
    private MonsterFamily validFamily;
    private Archetype validArchetype;

    @BeforeEach
    public void setUp() {

        generator = new MonsterGeneratorService();
        
        validFamily = FamilyRepository.loadAllFamilies().stream()
                .filter(f -> f.familyName().equals("Undead"))
                .findFirst()
                .orElseThrow();
                
        validArchetype = ArchetypeRepository.loadAllArchetypes().stream()
                .filter(a -> a.roleName().equals("Brute"))
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void givenValidInputs_whenGenerateMonster_thenReturnFullyPopulatedMonster() {

        int targetLevel = 7;


        Monster generatedMonster = generator.generateMonster(validFamily, validArchetype, targetLevel);


        System.out.println("=========================================");
        System.out.println("           ENCOUNTER GENERATED           ");
        System.out.println("=========================================");
        System.out.println("Name: " + generatedMonster.name());
        System.out.println("Level: " + generatedMonster.level());
        System.out.println("HP: " + generatedMonster.hitPoints());
        System.out.println("=========================================");

        assertNotNull(generatedMonster);
        assertEquals(7, generatedMonster.level());
        
        assertNotNull(generatedMonster.name());
        assertFalse(generatedMonster.name().isBlank());
    }

    @Test
    public void givenLevelAbove25_whenGenerateMonster_thenThrowIllegalArgumentException() {

        int invalidLevel = 999; 


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.generateMonster(validFamily, validArchetype, invalidLevel);
        });
        assertEquals("Security Exception: Level must be strictly between -1 and 25. Received: 999", exception.getMessage());
    }

    @Test
    public void givenLevelBelowMinus1_whenGenerateMonster_thenThrowIllegalArgumentException() {

        int invalidLevel = -2; 


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.generateMonster(validFamily, validArchetype, invalidLevel);
        });
        assertEquals("Security Exception: Level must be strictly between -1 and 25. Received: -2", exception.getMessage());
    }

    @Test
    public void givenNullFamily_whenGenerateMonster_thenThrowIllegalArgumentException() {

        MonsterFamily nullFamily = null;
        int targetLevel = 7;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.generateMonster(nullFamily, validArchetype, targetLevel);
        });
        assertEquals("Security Exception: MonsterFamily cannot be null.", exception.getMessage());
    }

    @Test
    public void givenNullArchetype_whenGenerateMonster_thenThrowIllegalArgumentException() {

        Archetype nullArchetype = null;
        int targetLevel = 7;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            generator.generateMonster(validFamily, nullArchetype, targetLevel);
        });
        assertEquals("Security Exception: Archetype cannot be null.", exception.getMessage());
    }
}
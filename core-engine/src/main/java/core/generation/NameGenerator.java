package core.generation;

import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator {

    private static final String[] PREFIXES = {
        "Zar", "Mor", "Vrak", "Kael", "Thal", "Gor", "Xan", "Drak", "Bael", "Vor"
    };
    
    private static final String[] SUFFIXES = {
        "vox", "grom", "thos", "zor", "drak", "bane", "grim", "thor", "vash", "zom"
    };
    
    private static final String[] EPITHETS = {
        "the Undying", "the Vile", "the Swift", "the Brutal", "the Ancient", 
        "the Defiler", "the Relentless", "the Shadow", "the Devourer", "the Cursed"
    };

    public static String generateRandomName() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        
        String prefix = PREFIXES[rnd.nextInt(PREFIXES.length)];
        String suffix = SUFFIXES[rnd.nextInt(SUFFIXES.length)];
        String epithet = EPITHETS[rnd.nextInt(EPITHETS.length)];
        
        return prefix + suffix + " " + epithet;
    }
}
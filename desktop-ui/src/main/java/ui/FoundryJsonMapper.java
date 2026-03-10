package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import core.domain.Monster;

public class FoundryJsonMapper {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Monster m) {
        JsonObject root = new JsonObject();

        
        root.addProperty("name", m.name());
        root.addProperty("traits", m.familyName().toLowerCase() + ", " + m.archetypeName().toLowerCase());
        root.addProperty("level", m.level());
        root.addProperty("alignment", "n");
        root.addProperty("size", "medium");
        root.addProperty("type", "humanoid");
        
      
        root.addProperty("description", "");
        root.addProperty("items", "");
        root.addProperty("speed", m.speed() > 0 ? String.valueOf(m.speed()) : "");
        root.addProperty("languages", String.join(", ", m.languages()));

        root.add("strength", createStatBlock(formatMod(m.strength()), "moderate"));
        root.add("dexterity", createStatBlock(formatMod(m.dexterity()), "moderate"));
        root.add("constitution", createStatBlock(formatMod(m.constitution()), "moderate"));
        root.add("intelligence", createStatBlock(formatMod(m.intelligence()), "moderate"));
        root.add("wisdom", createStatBlock(formatMod(m.wisdom()), "moderate"));
        root.add("charisma", createStatBlock(formatMod(m.charisma()), "moderate"));
        
        root.add("perception", createStatBlock(formatMod(m.perception()), "moderate"));

        root.add("ac", createStatBlock(String.valueOf(m.armorClass()), "moderate"));
        root.add("hp", createStatBlock(String.valueOf(m.hitPoints()), "moderate"));
        root.add("fortitude", createStatBlock(formatMod(m.fortitudeSave()), "moderate"));
        root.add("reflex", createStatBlock(formatMod(m.reflexSave()), "moderate"));
        root.add("will", createStatBlock(formatMod(m.willSave()), "moderate"));


        String[] emptySkills = {"acrobatics", "arcana", "athletics", "crafting", "deception", 
                                "diplomacy", "intimidation", "medicine", "nature", "occultism", 
                                "performance", "religion", "society", "stealth", "survival", "thievery"};
        for (String skill : emptySkills) {
            root.add(skill, createStatBlock("", "moderate"));
        }

        return gson.toJson(root);
    }


    private static JsonObject createStatBlock(String value, String benchmark) {
        JsonObject obj = new JsonObject();
        obj.addProperty("value", value);
        obj.addProperty("benchmark", benchmark);
        obj.addProperty("note", "");
        return obj;
    }


    private static String formatMod(int modifier) {
        return modifier >= 0 ? "+" + modifier : String.valueOf(modifier);
    }
}
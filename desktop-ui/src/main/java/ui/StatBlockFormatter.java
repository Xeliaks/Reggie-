package ui;

import core.domain.Monster;

public class StatBlockFormatter {

    public static String format(Monster m) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append(m.name().toUpperCase()).append("\n");
        sb.append("Level ").append(m.level()).append(" ")
          .append(m.familyName()).append(" ")
          .append(m.archetypeName()).append("\n");
        sb.append("==================================================\n");
        
        sb.append("Perception ").append(formatMod(m.perception())).append("; ");
        if (!m.senses().isEmpty()) sb.append(String.join(", ", m.senses().toArray(new String[0])));
        sb.append("\n");
        
        if (!m.languages().isEmpty()) {
            sb.append("Languages: ").append(String.join(", ", m.languages().toArray(new String[0]))).append("\n");
        }
        sb.append("Skills: (Auto-calculated)\n");
        
        sb.append(String.format("Str %s, Dex %s, Con %s, Int %s, Wis %s, Cha %s\n",
                formatMod(m.strength()), formatMod(m.dexterity()), formatMod(m.constitution()), 
                formatMod(m.intelligence()), formatMod(m.wisdom()), formatMod(m.charisma())));
        
        sb.append("--------------------------------------------------\n");
        sb.append("AC ").append(m.armorClass()).append("; ");
        sb.append("Fort ").append(formatMod(m.fortitudeSave())).append(", ");
        sb.append("Ref ").append(formatMod(m.reflexSave())).append(", ");
        sb.append("Will ").append(formatMod(m.willSave())).append("\n");
        
        sb.append("HP ").append(m.hitPoints());
        if (!m.immunities().isEmpty()) sb.append("; Immunities: ").append(String.join(", ", m.immunities().toArray(new String[0])));
        if (!m.resistances().isEmpty()) sb.append("; Resistances: ").append(String.join(", ", m.resistances().toArray(new String[0])));
        if (!m.weaknesses().isEmpty()) sb.append("; Weaknesses: ").append(String.join(", ", m.weaknesses().toArray(new String[0])));
        sb.append("\n");
        
        sb.append("--------------------------------------------------\n");
        sb.append("Speed ").append(m.speed()).append(" feet");
        if (!m.movementTypes().isEmpty()) sb.append(" (").append(String.join(", ", m.movementTypes().toArray(new String[0]))).append(")");
        sb.append("\n");
        
        sb.append("Melee Strike ").append(formatMod(m.strikeBonus())).append(" (Damage: ").append(m.strikeDamage()).append(")\n");
        
        if (!m.specialAbilities().isEmpty()) {
            sb.append("\nSpecial Abilities:\n");
            m.specialAbilities().forEach(ability -> sb.append("- ").append(ability).append("\n"));
        }
        sb.append("==================================================");
        
        return sb.toString();
    }


    public static String formatHtml(Monster m) {
        String css = """
            <style>
                body { font-family: 'Segoe UI', Helvetica, sans-serif; font-size: 14px; color: #000000; background-color: #ffffff; padding: 10px; margin: 0; }
                h1 { font-family: 'Georgia', serif; font-size: 26px; font-weight: 900; color: #000000; margin: 0; text-transform: uppercase; display: inline-block; }
                .level { float: right; font-family: 'Georgia', serif; font-size: 22px; font-weight: 900; color: #000000; margin: 0; display: inline-block; }
                .trait-box { background-color: #000000; color: #ffffff; padding: 3px 6px; border-radius: 2px; font-weight: bold; font-size: 12px; margin-right: 4px; text-transform: uppercase; letter-spacing: 0.5px; display: inline-block; }
                hr { border: 0; height: 2px; background-color: #000000; margin: 10px 0; }
                .stat-line { margin-bottom: 5px; line-height: 1.4; }
                .bold { font-weight: bold; font-family: 'Georgia', serif; }
            </style>
            """;

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>").append(css).append("</head><body>");
        
        sb.append("<div><h1>").append(m.name()).append("</h1>");
        sb.append("<span class='level'>CREATURE ").append(m.level()).append("</span></div>");
        
        sb.append("<div style='margin-top: 6px; margin-bottom: 8px;'>");
        sb.append("<span class='trait-box'>N</span>"); 
        sb.append("<span class='trait-box'>MEDIUM</span>"); 
        sb.append("<span class='trait-box'>HUMANOID</span>");
        sb.append("<span class='trait-box'>").append(m.familyName()).append("</span>");
        if (!m.archetypeName().equalsIgnoreCase("Standard")) {
            sb.append("<span class='trait-box'>").append(m.archetypeName()).append("</span>");
        }
        sb.append("</div>");
        
        sb.append("<hr>");
        
        sb.append("<div class='stat-line'><span class='bold'>Perception</span> ").append(formatMod(m.perception()));
        if (!m.senses().isEmpty()) sb.append("; ").append(String.join(", ", m.senses().toArray(new String[0])));
        sb.append("</div>");
        
        if (!m.languages().isEmpty()) {
            sb.append("<div class='stat-line'><span class='bold'>Languages</span> ").append(String.join(", ", m.languages().toArray(new String[0]))).append("</div>");
        }
        
        sb.append("<div class='stat-line'><span class='bold'>Skills</span> (Auto-calculated)</div>");
        sb.append("<div class='stat-line'><span class='bold'>Str</span> ").append(formatMod(m.strength()))
          .append(", <span class='bold'>Dex</span> ").append(formatMod(m.dexterity()))
          .append(", <span class='bold'>Con</span> ").append(formatMod(m.constitution()))
          .append(", <span class='bold'>Int</span> ").append(formatMod(m.intelligence()))
          .append(", <span class='bold'>Wis</span> ").append(formatMod(m.wisdom()))
          .append(", <span class='bold'>Cha</span> ").append(formatMod(m.charisma()))
          .append("</div>");
          
        sb.append("<hr>");
        
        sb.append("<div class='stat-line'><span class='bold'>AC</span> ").append(m.armorClass())
          .append("; <span class='bold'>Fort</span> ").append(formatMod(m.fortitudeSave()))
          .append(", <span class='bold'>Ref</span> ").append(formatMod(m.reflexSave()))
          .append(", <span class='bold'>Will</span> ").append(formatMod(m.willSave())).append("</div>");
          
        sb.append("<div class='stat-line'><span class='bold'>HP</span> ").append(m.hitPoints());
        if (!m.immunities().isEmpty()) sb.append("; <span class='bold'>Immunities</span> ").append(String.join(", ", m.immunities().toArray(new String[0])));
        if (!m.resistances().isEmpty()) sb.append("; <span class='bold'>Resistances</span> ").append(String.join(", ", m.resistances().toArray(new String[0])));
        if (!m.weaknesses().isEmpty()) sb.append("; <span class='bold'>Weaknesses</span> ").append(String.join(", ", m.weaknesses().toArray(new String[0])));
        sb.append("</div>");
        
        sb.append("<hr>");
        
        sb.append("<div class='stat-line'><span class='bold'>Speed</span> ").append(m.speed()).append(" feet");
        if (!m.movementTypes().isEmpty()) sb.append(" (").append(String.join(", ", m.movementTypes().toArray(new String[0]))).append(")");
        sb.append("</div>");
        
        sb.append("<div class='stat-line'><span class='bold'>Melee</span> <img src='data:image/svg+xml;utf8,<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\" width=\"14\" height=\"14\"><path d=\"M10 0L20 10L10 20L0 10Z\" fill=\"%23000000\"/></svg>' style='vertical-align: middle;'> ")
          .append(formatMod(m.strikeBonus())).append(" <span class='bold'>Damage</span> ").append(m.strikeDamage()).append("</div>");
          
        if (!m.specialAbilities().isEmpty()) {
            sb.append("<br>");
            m.specialAbilities().forEach(ability -> 
                sb.append("<div class='stat-line'><span class='bold'>").append(ability).append("</span> <img src='data:image/svg+xml;utf8,<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\" width=\"14\" height=\"14\"><path d=\"M10 0L20 10L10 20L0 10Z\" fill=\"%23000000\"/></svg>' style='vertical-align: middle;'> </div>")
            );
        }
        
        sb.append("</body></html>");
        return sb.toString();
    }


    private static String formatMod(int modifier) {
        return modifier >= 0 ? "+" + modifier : String.valueOf(modifier);
    }
}
package ui;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import core.domain.Monster;

public class PdfExportService {

    private static final PDType1Font FONT_BOLD = PDType1Font.HELVETICA_BOLD;
    private static final PDType1Font FONT_REG = PDType1Font.HELVETICA;
    private static final int FONT_SIZE = 11;
    private static final float MARGIN = 50;

    public static void generateMonsterPdf(Monster m, File targetFile) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
                float width = page.getMediaBox().getWidth() - (2 * MARGIN);
                float y = page.getMediaBox().getHeight() - MARGIN;


                cs.beginText();
                cs.setFont(FONT_BOLD, 24);
                cs.newLineAtOffset(MARGIN, y);
                cs.showText(m.name().toUpperCase());
                cs.endText();

                String levelText = "CREATURE " + m.level();
                float levelWidth = FONT_BOLD.getStringWidth(levelText) / 1000 * 20;
                cs.beginText();
                cs.setFont(FONT_BOLD, 20);
                cs.newLineAtOffset(MARGIN + width - levelWidth, y);
                cs.showText(levelText);
                cs.endText();

                y -= 25;


                List<String> traits = new ArrayList<>();
                traits.add("N");
                traits.add("MEDIUM");
                traits.add("HUMANOID");
                traits.add(m.familyName().toUpperCase());
                if (!m.archetypeName().equalsIgnoreCase("Standard")) {
                    traits.add(m.archetypeName().toUpperCase());
                }

                float currentX = MARGIN;
                cs.setFont(FONT_BOLD, 10);
                for (String trait : traits) {
                    float tw = FONT_BOLD.getStringWidth(trait) / 1000 * 10;
                    float boxWidth = tw + 8;
                    float boxHeight = 14;

                    // Draw Black Box
                    cs.setNonStrokingColor(Color.BLACK);
                    cs.addRect(currentX, y - 3, boxWidth, boxHeight);
                    cs.fill();

                    cs.setNonStrokingColor(Color.WHITE);
                    cs.beginText();
                    cs.newLineAtOffset(currentX + 4, y);
                    cs.showText(trait);
                    cs.endText();

                    currentX += boxWidth + 4;
                }

                cs.setNonStrokingColor(Color.BLACK);
                y -= 15;
                drawSeparator(cs, width, y);
                y -= 20;


                String sensesStr = formatMod(m.perception()) + (!m.senses().isEmpty() ? "; " + String.join(", ", m.senses()) : "");
                drawBoldLabelText(cs, MARGIN, y, "Perception ", sensesStr);
                y -= 15;
                
                if (!m.languages().isEmpty()) {
                    drawBoldLabelText(cs, MARGIN, y, "Languages ", String.join(", ", m.languages()));
                    y -= 15;
                }

                drawBoldLabelText(cs, MARGIN, y, "Skills ", "(Auto-calculated)");
                y -= 15;

                String attributes = String.format("Str %s, Dex %s, Con %s, Int %s, Wis %s, Cha %s",
                        formatMod(m.strength()), formatMod(m.dexterity()), formatMod(m.constitution()),
                        formatMod(m.intelligence()), formatMod(m.wisdom()), formatMod(m.charisma()));
                drawBoldLabelText(cs, MARGIN, y, "", attributes);

                y -= 10;
                drawSeparator(cs, width, y);
                y -= 20;


                String defenses = String.format("AC %d; Fort %s, Ref %s, Will %s",
                        m.armorClass(), formatMod(m.fortitudeSave()), formatMod(m.reflexSave()), formatMod(m.willSave()));
                drawBoldLabelText(cs, MARGIN, y, "", defenses); 
                y -= 15;

                StringBuilder hpLine = new StringBuilder(String.valueOf(m.hitPoints()));
                if (!m.immunities().isEmpty()) hpLine.append("; Immunities ").append(String.join(", ", m.immunities()));
                if (!m.resistances().isEmpty()) hpLine.append("; Resistances ").append(String.join(", ", m.resistances()));
                if (!m.weaknesses().isEmpty()) hpLine.append("; Weaknesses ").append(String.join(", ", m.weaknesses()));
                drawBoldLabelText(cs, MARGIN, y, "HP ", hpLine.toString());

                y -= 10;
                drawSeparator(cs, width, y);
                y -= 20;


                String speedStr = m.speed() + " feet" + (!m.movementTypes().isEmpty() ? " (" + String.join(", ", m.movementTypes()) + ")" : "");
                drawBoldLabelText(cs, MARGIN, y, "Speed ", speedStr);
                y -= 20;


                drawBoldLabelText(cs, MARGIN, y, "Melee ", "");
                float meleeWidth = FONT_BOLD.getStringWidth("Melee ") / 1000 * FONT_SIZE;
                

                drawDiamond(cs, MARGIN + meleeWidth + 6, y + 3);
                
                String strikeText = formatMod(m.strikeBonus()) + " Damage " + m.strikeDamage();
                cs.beginText();
                cs.setFont(FONT_REG, FONT_SIZE);
                cs.newLineAtOffset(MARGIN + meleeWidth + 16, y);
                cs.showText(strikeText);
                cs.endText();


                if (!m.specialAbilities().isEmpty()) {
                    y -= 20;
                    for (String ability : m.specialAbilities()) {
                        drawBoldLabelText(cs, MARGIN, y, ability + " ", "");
                        float abilityWidth = FONT_BOLD.getStringWidth(ability + " ") / 1000 * FONT_SIZE;
                        drawDiamond(cs, MARGIN + abilityWidth + 6, y + 3);
                        y -= 15;
                    }
                }
            }
            document.save(targetFile);
        }
    }


    private static void drawSeparator(PDPageContentStream cs, float width, float y) throws IOException {
        cs.setLineWidth(2f);
        cs.moveTo(MARGIN, y);
        cs.lineTo(MARGIN + width, y);
        cs.stroke();
    }


    private static void drawBoldLabelText(PDPageContentStream cs, float x, float y, String boldPart, String regPart) throws IOException {
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.setFont(FONT_BOLD, FONT_SIZE);
        cs.showText(boldPart);
        cs.setFont(FONT_REG, FONT_SIZE);
        cs.showText(regPart);
        cs.endText();
    }


    private static void drawDiamond(PDPageContentStream cs, float x, float y) throws IOException {
        float size = 4;
        cs.setNonStrokingColor(Color.BLACK);
        cs.moveTo(x, y + size);
        cs.lineTo(x + size, y);
        cs.lineTo(x, y - size);
        cs.lineTo(x - size, y);
        cs.fill();
    }

    private static String formatMod(int modifier) {
        return modifier >= 0 ? "+" + modifier : String.valueOf(modifier);
    }
}
package ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import core.domain.Monster;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class FileExportService {

    public static void saveStatBlock(Window ownerWindow, String txtContent, Monster monster) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Monster Stat Block");
        
        String safeName = monster.name().replaceAll("[^a-zA-Z0-9_-]", "_");
        fileChooser.setInitialFileName(safeName);
        
        FileChooser.ExtensionFilter extTxt = new FileChooser.ExtensionFilter("Text File (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extJson = new FileChooser.ExtensionFilter("FoundryVTT JSON (*.json)", "*.json");
        FileChooser.ExtensionFilter extPdf = new FileChooser.ExtensionFilter("Printable PDF (*.pdf)", "*.pdf");
        
        fileChooser.getExtensionFilters().addAll(extTxt, extJson, extPdf);
        
        File file = fileChooser.showSaveDialog(ownerWindow);
        if (file != null) {
            String extension = getExtension(file.getName());
            
            try {
                if ("json".equalsIgnoreCase(extension)) {
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(FoundryJsonMapper.toJson(monster));
                    }
                } else if ("pdf".equalsIgnoreCase(extension)) {
                    PdfExportService.generateMonsterPdf(monster, file);
                } else {
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(txtContent);
                    }
                }
            } catch (IOException ex) {
                System.err.println("Failed to save file: " + ex.getMessage());
            }
        }
    }

    private static String getExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i > 0 ? fileName.substring(i + 1) : "txt";
    }
}
package ui;

import java.util.List;

import core.api.MonsterGeneratorService;
import core.data.ArchetypeRepository;
import core.data.FamilyRepository;
import core.domain.Archetype;
import core.domain.Monster;
import core.domain.MonsterFamily;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView extends Application {

    private final MonsterGeneratorService generatorService = new MonsterGeneratorService();
    private List<MonsterFamily> families;
    private List<Archetype> archetypes;
    private Monster currentMonster; 

    @Override
    public void start(Stage primaryStage) {
      
        families = FamilyRepository.loadAllFamilies();
        archetypes = ArchetypeRepository.loadAllArchetypes();

        
        ControlPanelView controlPanel = new ControlPanelView(families, archetypes);
        StatBlockView statBlockView = new StatBlockView();

        
       controlPanel.getGenerateBtn().setOnAction(e -> {
            MonsterFamily family = families.stream()
                    .filter(f -> f.familyName().equals(controlPanel.getSelectedFamily()))
                    .findFirst().orElseThrow();
                    
            Archetype archetype = archetypes.stream()
                    .filter(a -> a.roleName().equals(controlPanel.getSelectedArchetype()))
                    .findFirst().orElseThrow();


            currentMonster = generatorService.generateMonster(family, archetype, controlPanel.getSelectedLevel());


            String html = StatBlockFormatter.formatHtml(currentMonster);
            String plainText = StatBlockFormatter.format(currentMonster);
            

            statBlockView.displayHtml(html);
            statBlockView.setPlainTextCache(plainText);
            

            controlPanel.getSaveBtn().setDisable(false);
        });

       
       controlPanel.getSaveBtn().setOnAction(e -> {
            if (currentMonster != null) {
                FileExportService.saveStatBlock(
                    primaryStage, 
                    statBlockView.getText(), 
                    currentMonster
                );
            }
        });

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setLeft(controlPanel);
        root.setCenter(statBlockView);
        BorderPane.setMargin(statBlockView, new Insets(0, 0, 0, 15));

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Reggie - PF2e Monster Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


package ui;

import java.util.List;

import core.domain.Archetype;
import core.domain.MonsterFamily;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControlPanelView extends VBox {
    private final ComboBox<String> familyCombo;
    private final ComboBox<String> archetypeCombo;
    private final Spinner<Integer> levelSpinner;
    private final Button generateBtn;
    private final Button saveBtn;

    public ControlPanelView(List<MonsterFamily> families, List<Archetype> archetypes) {
        this.setSpacing(15);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #d3d3d3; -fx-border-radius: 5;");
        this.setPrefWidth(250);

        Label titleLabel = new Label("Reggie");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        familyCombo = new ComboBox<>();
        families.forEach(f -> familyCombo.getItems().add(f.familyName()));
        familyCombo.getSelectionModel().selectFirst();
        familyCombo.setMaxWidth(Double.MAX_VALUE);

        archetypeCombo = new ComboBox<>();
        archetypes.forEach(a -> archetypeCombo.getItems().add(a.roleName()));
        archetypeCombo.getSelectionModel().selectFirst();
        archetypeCombo.setMaxWidth(Double.MAX_VALUE);

        levelSpinner = new Spinner<>(-1, 25, 1);
        levelSpinner.setMaxWidth(Double.MAX_VALUE);

        generateBtn = new Button("Generate Monster");
        generateBtn.setMaxWidth(Double.MAX_VALUE);
        generateBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold;");

        saveBtn = new Button("Save");
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        saveBtn.setDisable(true); 

        this.getChildren().addAll(
            titleLabel, new Separator(),
            new Label("Monster Family:"), familyCombo,
            new Label("Combat Archetype:"), archetypeCombo,
            new Label("Target Level:"), levelSpinner,
            new Region(), 
            generateBtn, saveBtn
        );
    }

   
    public String getSelectedFamily() { return familyCombo.getValue(); }
    public String getSelectedArchetype() { return archetypeCombo.getValue(); }
    public int getSelectedLevel() { return levelSpinner.getValue(); }
    public Button getGenerateBtn() { return generateBtn; }
    public Button getSaveBtn() { return saveBtn; }
}
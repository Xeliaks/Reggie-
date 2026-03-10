package ui;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

public class StatBlockView extends StackPane {
    private final WebView webView;
    private String plainTextCache = "";

    public StatBlockView() {
        webView = new WebView();
        webView.setContextMenuEnabled(false); 
        this.getChildren().add(webView);
        this.setStyle("-fx-border-color: #d3d3d3; -fx-border-width: 1px; -fx-background-color: white;");
    }


    public void displayHtml(String htmlContent) {
        webView.getEngine().loadContent(htmlContent);
    }


    public void setPlainTextCache(String plainText) {
        this.plainTextCache = plainText;
    }


    public String getText() {
        return plainTextCache;
    }
}
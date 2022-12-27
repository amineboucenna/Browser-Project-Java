import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private WebView myweb;
    @FXML
    private MenuItem darkmode;
    @FXML
    private CheckMenuItem DarkModeMenuItem;
    @FXML
    private VBox myVbox;
    @FXML
    private TabPane mytabpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AddTab(null);
    }

    public void Darkmode(ActionEvent e) {

        Scene scene = (Scene) myVbox.getScene();
        if (DarkModeMenuItem.isSelected()) {
            scene.getStylesheets().add("darkmode.css");

        } else {
            scene.getStylesheets().remove("darkmode.css");
        }
    }

    public void About(ActionEvent e) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("This browser was made by @amineboucenna");
        alert.setTitle("About");
        alert.setResult(ButtonType.OK);
        alert.show();
    }

    public void CloseAllTabs(ActionEvent e) {
        mytabpane.getTabs().clear();
    }

    public void CloseCurrentTab(ActionEvent e) {
       
        if (mytabpane.getTabs().size()>0) {
            mytabpane.getTabs().remove(SelectedTab(mytabpane));
            }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No tabs to delete");
        }

    }

    public void AddTab(ActionEvent e) {
        Tab tab = new Tab("Tab " + (mytabpane.getTabs().size() + 1));
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://google.com");
        VBox vBox2 = new VBox(webView);
        vBox2.setPrefSize(0, 0);
        tab.setContent(vBox2);
        mytabpane.getTabs().add(tab);
    }

    public void ReloadTab(ActionEvent event){
        Tab tab = mytabpane.getTabs().get(SelectedTab(mytabpane));
        VBox vbox = (VBox) tab.getContent();
        WebView webView = (WebView) vbox.lookup("Webview");
        WebEngine webEngine = webView.getEngine();
        webEngine.reload();
    }


    public void CloseProgram(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setContentText("Do you want to exit the browser");
        if( alert.showAndWait().get() != ButtonType.OK ) 
        {
            Stage stage = (Stage) myVbox.getScene().getWindow();
            stage.close();
        }
    }

    public int SelectedTab(TabPane tabPane){
            for (int i = 0; i < tabPane.getTabs().size(); i++) {
                Tab tab = tabPane.getTabs().get(i);
                if (tab.isSelected()) return i;
            }
            return -1;
    }


}

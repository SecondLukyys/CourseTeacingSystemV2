package view;

import com.example._1labdarbas.LaboratorinisDarbas1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.KursuMokymoSistema;
import model.Vartotojas;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PapildomasLangas implements Initializable {

    @FXML
    public Button button;
    @FXML
    public Button buttonToMainMenu;
    @FXML
    private Label label1;

    private KursuMokymoSistema kursuMokymoSistema;
    private String login;
    private Vartotojas user;
    private boolean admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setCourseData(KursuMokymoSistema kursuMokymoSistema, Vartotojas user, boolean admin) {
        this.kursuMokymoSistema = kursuMokymoSistema;
        this.user = user;
        this.admin = admin;
    }

    @FXML
    private void testFunk(ActionEvent event) {
        label1.setText("Labelio1 tekstas");
    }

    @FXML
    public void chooseColour(ActionEvent event) {

        Popup popup = new Popup();
        popup.setX(50);
        popup.setY(50);
         }

    public void goBackToMainMenu(ActionEvent event) throws IOException, ClassNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("pagrindinis-sistemos-langas.fxml"));
        Parent root = fxmlLoader.load();

        PagrindinisSistemosLangas pagrindinisSistemosLangas = fxmlLoader.getController();
        pagrindinisSistemosLangas.setCourseData(kursuMokymoSistema, user.getLogin(), user, admin);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonToMainMenu.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}

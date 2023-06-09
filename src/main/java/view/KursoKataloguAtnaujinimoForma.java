package view;

import com.example._1labdarbas.LaboratorinisDarbas1;
import controller.KatalogoValdiklis;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Katalogas;
import model.Kursas;
import model.KursuMokymoSistema;
import model.Vartotojas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KursoKataloguAtnaujinimoForma {


    public Button buttonBack;
    public ListView listView;
    public Button addCatalogue;
    public Button removeCatalogue;
    private KursuMokymoSistema kursuMokymoSistema;
    private String login;
    private Vartotojas user;
    boolean admin;
    private Kursas kursas;


    public void setCourseData(KursuMokymoSistema kursuMokymoSistema, Vartotojas user, Kursas kursas , boolean admin) throws IOException {
        this.kursuMokymoSistema = kursuMokymoSistema;
        this.user = user;
        this.kursas = kursas;
        this.admin = admin;
        fillTables();

        System.out.println(kursas.getId());
    }

    private void fillTables() throws IOException {

        listView.getItems().clear();
        for (Katalogas k : kursas.getCatalogue()) {
            listView.getItems().add(k.toString());
        }
    }

     public void backToMainMenu(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("pagrindinis-sistemos-langas.fxml"));
        Parent root = fxmlLoader.load();

        PagrindinisSistemosLangas pagrindinisSistemosLangas = fxmlLoader.getController();
        pagrindinisSistemosLangas.setCourseData(kursuMokymoSistema,login ,user, admin);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setTitle("Main course menu");
        stage.setScene(scene);
    }

    public void addCatalogue(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("naujo-katalogo-forma.fxml"));
        Parent root = fxmlLoader.load();

        NaujoKatalogoForma naujoKatalogoForma = fxmlLoader.getController();
        naujoKatalogoForma.setCourseData(kursuMokymoSistema ,user ,kursas, admin);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setTitle("New catalogue menu");
        stage.setScene(scene);

    }

    public void removeCatalogue(ActionEvent event) throws IOException {

    //login for catalogue removal
        FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("trinamo-katalogo-pasirinkimas.fxml"));
        Parent root = fxmlLoader.load();

        TrinamoKatalogoPasirinkimas trinamoKatalogoPasirinkimas = fxmlLoader.getController();
        trinamoKatalogoPasirinkimas.setCourseData(kursuMokymoSistema ,user ,kursas, admin);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setTitle("New catalogue menu");
        stage.setScene(scene);


    }
}

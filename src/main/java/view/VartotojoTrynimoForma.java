package view;

import com.example._1labdarbas.LaboratorinisDarbas1;
import hibControllers.VartotojasHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Kursas;
import model.KursuMokymoSistema;
import model.Vartotojas;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

public class VartotojoTrynimoForma {

    public Button buttonDelete;
    public Button buttonBack;
    public TextField loginF;
    public TextField passwordF;
    private KursuMokymoSistema kursuMokymoSistema;
    private Vartotojas user;
    private String login;
    private boolean admin;

    public void setCourseData(KursuMokymoSistema kursuMokymoSistema, String login, Vartotojas user, boolean admin) {
        this.kursuMokymoSistema = kursuMokymoSistema;
        this.login = login;
        this.user = user;
        this.admin = admin;
    }


    public void deleteAndBackToMainMenu(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        Vartotojas user = kursuMokymoSistema.getAllSystemUsers().stream().filter(k -> k.getLogin().equals(loginF.getText())).filter(k -> k.getPassword().equals(passwordF.getText())).findFirst().orElse(null);

        if (user != null) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
            VartotojasHibController vartotojasHibController = new VartotojasHibController(entityManagerFactory);

            vartotojasHibController.removeUser(loginF.getText(), passwordF.getText());

            entityManagerFactory.close();

            FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("pagrindinis-sistemos-langas.fxml"));
            Parent root = fxmlLoader.load();

            kursuMokymoSistema.getAllSystemUsers().remove(user);
            PrisijungimoLangas.alertMessage("User removed");

            PagrindinisSistemosLangas pagrindinisSistemosLangas = fxmlLoader.getController();
            pagrindinisSistemosLangas.setCourseData(kursuMokymoSistema, login, user, admin);

            Scene scene = new Scene(root);
            Stage stage = (Stage) buttonDelete.getScene().getWindow();
            stage.setTitle("Course Management System");
            stage.setScene(scene);

        } else {
            PrisijungimoLangas.alertMessage("Wrong input data, no such user found");
        }
    }


    public void BackToMainMenu(ActionEvent event) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(LaboratorinisDarbas1.class.getResource("pagrindinis-sistemos-langas.fxml"));
        Parent root = fxmlLoader.load();

        PagrindinisSistemosLangas pagrindinisSistemosLangas = fxmlLoader.getController();
        pagrindinisSistemosLangas.setCourseData(kursuMokymoSistema,login ,user, admin);

        Scene scene = new Scene(root);
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.setTitle("Main course menu");
        stage.setScene(scene);
    }



}

package com.example._1labdarbas;

import controller.DuomenuBaziuUtil;
import controller.KursoValdiklis;
import controller.SkaitymasRasymas;
import controller.VartotojoValdiklis;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FizinisAsmuo;
import model.Kursas;
import model.KursuMokymoSistema;
import model.Vartotojas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class LaboratorinisDarbas1 extends Application {

    public LaboratorinisDarbas1(){};
    public void init(){};

    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("prisijungimo-langas.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login menu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void stop(){
        Platform.exit();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch(); //grafine sasaja sukursiu pirma sukures pagrindine crud logika , kad nebutu dvieju main metodu

        Scanner sc = new Scanner(System.in);

        //KursuMokymoSistema kursuMokymoSistema = null;
        KursuMokymoSistema kursuMokymoSistema = SkaitymasRasymas.loadFromFile("dataFile.txt");
        if(kursuMokymoSistema == null){
         kursuMokymoSistema = new KursuMokymoSistema("Sistema", "V.1", new ArrayList<Vartotojas>(), new ArrayList<Kursas>());
        }

        Vartotojas admin = new FizinisAsmuo("admin", "admin", 1, LocalDate.now(), LocalDate.now(), true, "name", "surename", 22, "123456789", "emailAdress");
        kursuMokymoSistema.getAllSystemUsers().add(admin);

        String cmd = "";

        while (!cmd.equals("q")){
            System.out.println("Please choose an option: \n"
                    + "\t work with courses -- c \n"
                    + "\t work with users -- u \n"
                    + "\t save to file all system -- s \n"
                    + "\t end work -- q \n");

            cmd = sc.nextLine().toLowerCase();
            switch (cmd){
                case "c":
                    KursoValdiklis.generateCourseMenu(sc, kursuMokymoSistema, admin);
                    break;
                case "u":
                    VartotojoValdiklis.generateUserMenu(sc, kursuMokymoSistema);
                    break;
                case "s":
                    SkaitymasRasymas.writeToFile("dataFile.txt", kursuMokymoSistema);
                    break;
                case "q":
                    System.out.println("Finishing program. \n");
                    break;
                default:
                    System.out.println("Please choose a valid command");
            }

        }

        System.exit(0);
    }


}
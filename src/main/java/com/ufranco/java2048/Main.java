package com.ufranco.java2048;

import com.ufranco.java2048.frontend.ConfirmBox;
import com.ufranco.java2048.frontend.DesktopNotification;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.Arrays;

public class Main extends Application {

    Stage window;
    Scene scene1, scene2, scene3;
    GridPane grid;
    Button button1, button2, button3;
    Label labelMabel = new Label();
    DesktopNotification notification = new DesktopNotification(labelMabel);

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        setWindowProperties();
        setGrid();

        Scene scene = new Scene(grid);
        window.setScene(scene);

        window.show();

    }



    private void setWindowProperties()  {
        window.setTitle("Java2048");
        window.getIcons().add(
          new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/2048_logo.svg/1200px-2048_logo.svg.png")
        );
        window.setMinHeight(800);
        window.setMinWidth(600);
        window.setOnCloseRequest(e  -> {
            e.consume();
            closeProgram();
        });

    }

    private void setGrid() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        HBox topMenu = createTopMenu();
        GridPane.setConstraints(topMenu, 1,0);

        VBox leftMenu = createLeftMenu();
        GridPane.setConstraints(leftMenu, 0, 1);

        grid.getChildren().addAll(topMenu,leftMenu);
    }

    private HBox createTopMenu() {
        HBox topMenu = new HBox();
        Button fileButton = new Button("File");
        Button editButton = new Button("Edit");
        Button viewButton = new Button("View");
        Button helpButton = new Button("Help");
        topMenu.getChildren().addAll(
          fileButton,
          editButton,
          viewButton,
          helpButton
        );

        return topMenu;
    }

    private VBox createLeftMenu() {

        VBox leftMenu = new VBox();
        Button btnA = new Button("1");
        Button btnB = new Button("2");
        Button btnC = new Button("3");
        Button btnD = new Button("4");
        Button btnE = new Button("5");

        leftMenu.getChildren().addAll(
          btnA,
          btnB,
          btnC,
          btnD,
          btnE
        );

        return leftMenu;
    }

    private void closeProgram() {
        if(ConfirmBox.display("Cerrar programa", "De verdad queres cerrar el programa?")){
            window.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
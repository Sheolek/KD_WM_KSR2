package com.example.KSR2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main-view.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        root = fxmlLoader.load();
        applicationContext.stop();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("KSR");
        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        applicationContext.stop();
    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }
}

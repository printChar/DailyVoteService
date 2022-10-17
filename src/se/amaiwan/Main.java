package se.amaiwan;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.amaiwan.controller.VoteController;
import se.amaiwan.model.dao.Vote;
import se.amaiwan.model.repository.service.VoteService;
import se.amaiwan.view.VoteView;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

       // https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm

        VoteService model = new VoteService();
        VoteView view = new VoteView();
        VoteController voteViewController = new VoteController(model, view);

        primaryStage.setTitle("Service");
        Scene scene = new Scene(view, 500, 500);

        view.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

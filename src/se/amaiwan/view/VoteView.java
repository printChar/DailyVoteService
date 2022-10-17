package se.amaiwan.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteView extends BorderPane {

    //String path till ikonerna
    private final String BAD_ICON = "icons/baseline_mood_bad_white_18dp.png";
    private final String OKAY_ICON = "icons/baseline_sentiment_dissatisfied_white_18dp.png";
    private final String HAPPY_ICON  = "icons/baseline_sentiment_very_satisfied_white_18dp.png";

    //Röstknapparna
    private Button badBtn = getIconBtn(BAD_ICON);
    private Button okayBtn = getIconBtn(OKAY_ICON);
    private Button happyBtn = getIconBtn(HAPPY_ICON);

    //TeaxtArean för utskrift av textfilen
    private TextArea textArea = new TextArea();

    //Labelholder för rösträknare
    private Label badCounterLabel = getCounterLabel();
    private Label okayCounterLabel = getCounterLabel();
    private Label happyCounterLabel = getCounterLabel();

    //Menuitems för menyn
    private MenuItem menuSaveToTextFileItem = new MenuItem("Spara till textfil");
    private MenuItem menuShowTextFileItem = new MenuItem("Visa textfil");

    public VoteView() {
        setTop(topLayout());
        setCenter(centerLayout());
        setBottom(bottomLayout());
    }

    /**
     * En metod som skapar top-layouten i min BorderPane
     * @return gridpane
     */
    private GridPane topLayout(){

        GridPane gridPane = new GridPane();

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefWidth(500);
        Menu menu = new Menu("Meny");
        menu.getItems().addAll(menuSaveToTextFileItem, menuShowTextFileItem);
        menuBar.getMenus().add(menu);

        gridPane.add(menuBar, 0, 0);

        return gridPane;
    }
    /**
     * En metod som skapar center-layouten i min BorderPane
     * @return GridPane
     */
    private GridPane centerLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        Label header = new Label("Vad tyckte du om vår service idag?");
        header.setFont(Font.font("Cambria", 18));
        header.setTextFill(Color.WHITE);

        HBox btnHolder = getHBox(30);
        HBox voteHolder = getHBox(55);

        btnHolder.getChildren().addAll(badBtn, okayBtn, happyBtn);
        voteHolder.getChildren().addAll(badCounterLabel, okayCounterLabel, happyCounterLabel);

        gridPane.add(header, 0, 0);
        gridPane.add(btnHolder, 0, 1);
        gridPane.add(voteHolder, 0, 2);

        return gridPane;
    }

    /**
     * En metod som skapar center-layouten i min BorderPane
     *
     * @return GridPane
     */
    private GridPane bottomLayout() {
        GridPane gridPane = new GridPane();

        gridPane.add(textArea, 0, 9);

        return gridPane;
    }

    /**
     * @param spacing
     * @return HBox
     */
    private HBox getHBox(double spacing) {
        HBox hBox = new HBox();
        hBox.setMinHeight(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(spacing);

        return hBox;
    }

    /**
     * Skapar en icon-knapp utifrån String path som argument, med önskad mått och färg.
     * @param stringPath
     * @return Button
     */
    private Button getIconBtn(String stringPath) {

        Button button = null;

        try {
            BufferedImage imagePath = ImageIO.read(new File(stringPath));
            Image image = SwingFXUtils.toFXImage(imagePath, null);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(35);
            imageView.setPreserveRatio(true);
            button = new Button(null, imageView);
            button.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            button.setPadding(Insets.EMPTY);

        } catch (IOException e) {
            Logger.getLogger(VoteView.class.getName()).log(Level.SEVERE, null, e);
        }
        return button;
    }

    /**Skapar en Label med önskad färg och storlek
     * @return Label
     */
    private Label getCounterLabel() {
        Label label = new Label();
        label.setFont(Font.font("Cambria", 15));
        label.setTextFill(Color.WHITE);
        return label;
    }

    public void addHappyVoteBtnListener(EventHandler eventHandler) {
        happyBtn.setOnAction(eventHandler);
    }

    public void addOkayVoteBtnListener(EventHandler eventHandler) {
        okayBtn.setOnAction(eventHandler);
    }
    public void addBadVoteBtnListener(EventHandler eventHandler) {
        badBtn.setOnAction(eventHandler);
    }

    public Label getBadCounterLabel() {
        return badCounterLabel;
    }

    public Label getOkayCounterLabel() {
        return okayCounterLabel;
    }

    public Label getHappyCounterLabel() {
        return happyCounterLabel;
    }

    public Button getBadBtn() {
        return badBtn;
    }

    public Button getOkayBtn() {
        return okayBtn;
    }

    public Button getHappyBtn() {
        return happyBtn;
    }

    public void addMenuSaveToTextFileItem(EventHandler eventHandler) {
        menuSaveToTextFileItem.setOnAction(eventHandler);
    }

    public void addtMenuShowTextFileItem(EventHandler eventHandler) {
        menuShowTextFileItem.setOnAction(eventHandler);
    }

    public MenuItem getMenuSaveToTextFileItem() {
        return menuSaveToTextFileItem;
    }

    public MenuItem getMenuShowTextFileItem() {
        return menuShowTextFileItem;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}

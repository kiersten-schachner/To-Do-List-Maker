import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ToDoList extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        ArrayList<Activity> list = new ArrayList<Activity>();
        Label title = new Label("Create Your To-Do List");
        title.setFont(Font.font("Copperplate", 40));

        TextField nameInput = new TextField();
        Label nameLabel = new Label("What do you need to do? ");
        nameLabel.setFont(Font.font("Georgia"));
        TextField priorityInput = new TextField();
        Label priorityLabel = new Label("How important is it that you get this task done (1-10)? ");
        priorityLabel.setFont(Font.font("Georgia"));

        Button addToList = new Button();
        addToList.setText("Add task to to-do list");
        addToList.setFont(Font.font("Georgia"));
        Button sortList = new Button();
        sortList.setText("Sort to-do list");
        sortList.setFont(Font.font("Georgia"));
        Button saveToFile = new Button();
        saveToFile.setText("Save list to file");
        saveToFile.setFont(Font.font("Georgia"));
        Button resetAll = new Button();
        resetAll.setText("Reset list and delete file");
        resetAll.setFont(Font.font("Georgia"));

        String soundName = "sound.mp3";
        Media sound = new Media(new File(soundName).toURI().toString());
        MediaPlayer play = new MediaPlayer(sound);

        addToList.setOnAction(event -> {
            play.seek(play.getStartTime());
            play.play();
            String name = nameInput.getCharacters().toString();
            try {
                int priority = Integer.parseInt(priorityInput.getText());
                if (priority < 1 || priority > 10) {
                    throw new Exception("Type in a priority between 1 and 10.");
                }
                if (name.equals("")) {
                    throw new Exception("Make sure all information is filled in.");
                }
                Activity task = new Activity(name, priority);
                list.add(task);
            } catch (NumberFormatException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid input.");
                a.setContentText("Make sure your inputs for numerical answers are positive integers in order to add to list");
                a.showAndWait();
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Invalid input.");
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            nameInput.setText("");
            priorityInput.setText("");
        });

        sortList.setOnAction(event -> {
            play.seek(play.getStartTime());
            play.play();
            Collections.sort(list);
        });

        saveToFile.setOnAction(event -> {
            play.seek(play.getStartTime());
            play.play();
            File listFile = new File("List.txt");
            FileUtil.saveToFile(list, listFile);
        });

        resetAll.setOnAction(event -> {
            play.seek(play.getStartTime());
            play.play();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmation to Reset");
                a.setHeaderText(null);
                a.setContentText("Are you sure you want to reset everything?");
                Optional<ButtonType> action = a.showAndWait();
                if (action.isPresent() && action.get() == ButtonType.OK) {
                    list.clear();
                    nameInput.setText("");
                    priorityInput.setText("");
                    File listFile = new File("List.txt");
                    if (listFile.exists()) {
                        listFile.delete();
                    }
                }
        });

        HBox label = new HBox();
        label.getChildren().add(title);
        label.setAlignment(Pos.CENTER);

        HBox task = new HBox();
        task.getChildren().addAll(nameLabel, nameInput);
        task.setAlignment(Pos.CENTER);

        HBox priority = new HBox();
        priority.getChildren().addAll(priorityLabel, priorityInput);
        priority.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.getChildren().addAll(label, task, priority, addToList, sortList, saveToFile, resetAll);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightblue;");
        root.setSpacing(5);

        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("To-Do List Maker");
        stage.setScene(scene);
        stage.show();
    }
}
package Contacts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

class Info extends VBox {
    private TextField name;
    private TextField number;
    private TextField email;

    Info() {
        this.setPrefSize(400, 150);
        this.setStyle("-fx-background-color: #F0F8FF;");

        name = new TextField(); // create task name text field
        name.setPrefSize(400, 20); // set size of text field
        name.setStyle(
                "-fx-background-color: #DAE5EA; -fx-border-width: 1; -fx-border-color: #B2BEB5; -fx-font-size: 16; -fx-font-weight: bold");
        // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        name.setPadding(new Insets(10, 0, 10, 5)); // adds some padding to the text field
        name.setPromptText("Name: ");
        this.getChildren().add(name); // add textlabel to task

        number = new TextField(); // create task name text field
        number.setPrefSize(400, 20); // set size of text field
        number.setStyle(
                "-fx-background-color: #DAE5EA; -fx-border-width: 1; -fx-border-color: #B2BEB5; -fx-font-size: 16");
        // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        number.setPadding(new Insets(10, 0, 10, 5)); // adds some padding to the text field
        number.setPromptText("Phone Number: ");
        this.getChildren().add(number); // add textlabel to task

        email = new TextField(); // create task name text field
        email.setPrefSize(400, 20); // set size of text field
        email.setStyle(
                "-fx-background-color: #DAE5EA; -fx-border-width: 1; -fx-border-color: #B2BEB5; -fx-font-size: 16"); // texfield
        // index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        email.setPadding(new Insets(10, 0, 10, 5)); // adds some padding to the text field
        email.setPromptText("Email: ");
        this.getChildren().add(email); // add textlabel to task

    }

    public TextField getName() {
        return this.name;
    }

    public TextField getNumber() {
        return this.number;
    }

    public TextField getEmail() {
        return this.email;
    }
}

class Task extends HBox {

    private Info contactInfo;

    private Label index;

    private Button uploadButton;
    private Button deleteButton;

    Task() {
        // this.setPrefSize(400, 150); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");

        deleteButton = new Button("X");
        deleteButton.setTextAlignment(TextAlignment.CENTER);
        deleteButton.setPrefHeight(Double.MAX_VALUE);
        deleteButton.setStyle(
                "-fx-background-color: #FFA9A9; -fx-border-width: 0; -fx-border-color: #8B0000; -fx-font-weight: bold");
        this.getChildren().add(deleteButton);

        uploadButton = new Button("Upload Photo");
        uploadButton.setPrefSize(250, Double.MAX_VALUE);
        uploadButton.setPrefHeight(Double.MAX_VALUE);
        uploadButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 1; -fx-border-color: #B2BEB5");
        this.getChildren().add(uploadButton);

        contactInfo = new Info();
        this.getChildren().add(contactInfo);
    }

    public Button getDeleteButton() {
        return this.deleteButton;
    }

    public Button getUploadButton() {
        return this.uploadButton;
    }

    public Info getInfo() {
        return this.contactInfo;
    }

    public void addImage(ImageView image) {
        // image.setPreserveRatio(true);
        image.setFitHeight(100);
        image.setFitWidth(100);
        // image.setY(Double.MA)
        this.getChildren().set(1, image);

    }

}

class TaskList extends VBox {

    TaskList() {
        this.setSpacing(3); // sets spacing between tasks
        this.setPrefSize(750, 600);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void saveTasks() {
        try {
            FileWriter output = new FileWriter("Contacts.csv");
            output.write("Name,Phone Number,Email Address\n");
            for (int i = 0; i < this.getChildren().size(); i++) {
                if (this.getChildren().get(i) instanceof Task) {
                    String name = ((Task) this.getChildren().get(i)).getInfo().getName().getText();
                    String number = ((Task) this.getChildren().get(i)).getInfo().getNumber().getText();
                    String email = ((Task) this.getChildren().get(i)).getInfo().getEmail().getText();
                    String toWrite = name + "," + number + "," + email + "\n";
                    output.write(toWrite);
                }
            }
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void sortTasks() {

        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof Task) {
                tasks.add((Task) this.getChildren().get(i));
            }
        }
        Collections.sort(tasks, (task1, task2) -> {
            String name1 = task1.getInfo().getName().getText();
            String name2 = task2.getInfo().getName().getText();
            return (name1.toUpperCase()).compareTo(name2.toUpperCase());
        });
        this.getChildren().clear();
        this.getChildren().addAll(tasks);
    }

}

class Footer extends HBox {

    private Button addButton;
    private Button sortButton;
    private Button saveButton;

    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        // set a default style for buttons - background color, font size, italics
        String defaultButtonStyle = "-fx-background-color: #FFFFFF; -fx-font-weight: bold; -fx-border-width: 1; -fx-border-color: #B2BEB5";

        addButton = new Button("Add Contact"); // text displayed on add button
        addButton.setStyle(defaultButtonStyle); // styling the button

        sortButton = new Button("Sort by Name");
        sortButton.setStyle(defaultButtonStyle);

        saveButton = new Button("Export to CSV");
        saveButton.setStyle(defaultButtonStyle);

        this.getChildren().addAll(addButton, sortButton, saveButton);
        this.setAlignment(Pos.CENTER); // aligning the buttons to center
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getSortButton() {
        return sortButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}

class Header extends HBox {

    Header() {
        this.setPrefSize(400, 40); // Size of the header
        this.setStyle("-fx-background-color: #F0F8FF;");

        Text titleText = new Text("Contact Management App"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        titleText.setFont(Font.font("Arial"));
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class AppFrame extends BorderPane {

    private Header header;
    private Footer footer;
    private TaskList taskList;

    private Button addButton;
    private Button sortButton;
    private Button saveButton;
    // To display images
    private ImageView imageView;

    // To open a file dialog for selecting images
    private FileChooser fileChooser;

    AppFrame(Stage primaryStage) {
        // Initialise the header Object
        header = new Header();

        // Create a tasklist Object to hold the tasks
        taskList = new TaskList();

        // Initialise the Footer Object
        footer = new Footer();

        /*
         * Website Title: How to create a scroll pane using java fx
         * Source:
         * https://www.tutorialspoint.com/how-to-create-a-scroll-pane-using-javafx
         * Date: 10/5/1023
         * Usage: Example of a scroll pane to see how ScrollPane() works
         */
        ScrollPane scroll = new ScrollPane(taskList);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(scroll);
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        // Initialise Button Variables through the getters in Footer
        addButton = footer.getAddButton();
        sortButton = footer.getSortButton();
        saveButton = footer.getSaveButton();

        // Call Event Listeners for the Buttons
        addListeners(primaryStage);
    }

    private void uploadImage(Stage primaryStage) {
        fileChooser = new FileChooser();
        // Select which extensions are allowed
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());

            /*
             * TODO6: Set the selected image in imageView i.e. display the image.
             * Hint: To implement this, you can use the setImage() method of ImageView and
             * pass the selected image as an argument.
             */

            imageView.setImage(image);

        }
    }

    public void addListeners(Stage primaryStage) {

        // Add button functionality
        addButton.setOnAction(e -> {
            // Create a new task
            Task task = new Task();
            // Add task to tasklist
            taskList.getChildren().add(task);

            Button deleteButton = task.getDeleteButton();
            deleteButton.setOnAction(e1 -> {
                taskList.getChildren().remove(task);
            });

            Button uploadButton = task.getUploadButton();
            imageView = new ImageView();
            uploadButton.setOnAction(e2 -> {
                this.uploadImage(primaryStage);
                task.addImage(imageView);
            });
        });

        saveButton.setOnAction(e -> {
            taskList.saveTasks();
        });

        sortButton.setOnAction(e -> {
            taskList.sortTasks();
        });

    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // TaskList
        AppFrame root = new AppFrame(primaryStage);

        // Set the title of the app
        primaryStage.setTitle("Contacts");
        // Create scene of mentioned size with the border pane
        primaryStage.setScene(new Scene(root, 400, 800));
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
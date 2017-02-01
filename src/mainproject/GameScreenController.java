/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import File.*;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 * FXML Controller class`
 *
 * @author USER
 */
public class GameScreenController implements Initializable {
    //varaible declaration
    @FXML
    public Button doneButton;
    @FXML
    public Label characterLabel, description;
    @FXML
    public TextArea textBox;
    @FXML
    public TextField characterNameTextField;
    @FXML
    public CheckBox humanBox, pounderBox, maleBox, femaleBox, slimBox, fatBox, muscularBox, warriorBox, archerBox, knightBox;

    private ArrayList<String> allDescriptions = new ArrayList();
    public static String characterName, characterRace, characterGender, characterClass, characterBodyType;
    private MakeTextFile newFile;
    private final boolean[] conditions = {false, false, false, false, false};
   

    /**Method
     * Initializes the controller class.
     * pre:none
     * post: initializes some of the local instance variable
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Collections.addAll(allDescriptions,
                "Warrior is a class for greedy indivials: "
                + "\n It relies on strength and long melee weapons. "
                + "\n A Character using this class will gain bonuses on streagth and long melee weapon damage. "
                + "\n The prefered weapon of choice for this class is the long war axe",
                "Acher is a class for patient indivials. "
                + "\n It relies on distance and percition. "
                + "\n A Character using this class will gain bonuses in percision and long ranged weapon damage. "
                + "\n The perfered weapon of choice for this class is the Bow and throwing knifes.",
                "Knight is a class for paitent and greedy indivials."
                + " \n It relies on defence and armor. "
                + "\n A Character using this class will gain bonuses on defence and armor equipment."
                + "\n The prefered weapon of choice is the knight armor and the sort sword.",
                "Human is a very basic class",
                "Pounder is a class for angry indvials");

        newFile = new MakeTextFile("C:/Users/USER/Documents/NetBeansProjects/MainProject/src/files/test.txt");
        try {
            newFile.createNewFile();
            newFile.createInputStream();
        } catch (FileNotFoundException ex) {
        }

        textBox.setEditable(false);
        textBox.setText("Welcome Player!" + "\n");
        setTimer();

    }
    /**Method
     * creates a Timeline and sets and initializes all the components required to create a cycle to repeat a certain method corresponding to time
     * pre:none
     * post:A TimleLine has been created and played until it is over
     */
    public void setTimer() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> displayGuide()));
        timeline.setCycleCount(3);
        timeline.play();

    }
    /**Method
     * displays a line from a certain file
     * pre:a Timeline must be created
     * post: displayed a line from a file
     * 
     */
    public void displayGuide() {
        try {
            textBox.appendText(newFile.OutPutReadLine() + "\n");

        } catch (IOException ex) {
            System.err.print("Error Found");
        }
    }
    /**Method
     * checks if the name user entered is valid
     * pre:the user must have entered a name 
     * post:the username has been checked over
     * @param event 
     */
    @FXML
    public void EnteredCharacterName(ActionEvent event) {
        if (!characterNameTextField.getText().matches("[a-zA-Z]+")) {
            characterLabel.setText("Your Character Name Can't Be Nothing,\n  contain numbers and spaces ");
            

        } else {
            conditions[4] = true;
            characterName = characterNameTextField.getText();
            characterLabel.setText("Your Character Name is: " + characterName);

        }

        
    }
    /**Method
     * displays warrior description
     * pre:the cursor must have moved over to the warrior checkBox
     * post: the warrior description has been displayed
     * @param eve 
     */
    @FXML
    public void warriorDescription(Event eve) {
        description.setText(allDescriptions.get(0));
    }
    /**Method
     * displays archer description
     * pre:the cursor must have moved over to the archer checkBox
     * post: the archer description has been displayed
     * @param event1 
     */
    @FXML
    public void archerDescription(Event event1) {
        description.setText(allDescriptions.get(1));
    }
    /**Method
     * displays knight description
     * pre:the cursor must have moved over to the knight  checkBox
     * post: the knight description has been displayed
     * @param event
     */
    @FXML
    public void knightDescription(Event event) {
        description.setText(allDescriptions.get(2));
    }
    /**Method
     * displays human description
     * pre:the cursor must have moved over to the human checkBox
     * post: the human description has been displayed
     * @param eve 
     */
    @FXML
    public void humanDescription(Event event) {
        description.setText(allDescriptions.get(3));
    }
    /**Method
     * displays pounder description
     * pre:the cursor must have moved over to the pounder checkBox
     * post: the pounder description has been displayed
     * @param eve 
     */
    @FXML
    public void pounderDescription(Event event) {
        description.setText(allDescriptions.get(4));
    }
    /**Method
     * checks if user has picked any of the race checkBoxes
     * pre:the user must have picked a race checkBox
     * post: sets the character race according to what the user has picked
     * @param event 
     */
    @FXML
    public void checkEventChoseRace(Event event) {
        conditions[0] = true;
        if (humanBox.isSelected()) {
            characterRace = "Human";
            pounderBox.setSelected(false);
        } else if (pounderBox.isSelected()) {
            characterRace = "Pounder";
            humanBox.setSelected(false);
        }
    }
    /**Method
     * checks if user has picked any of the gender checkBoxes
     * pre:the user must have picked a gender checkBox
     * post: sets the character gender according to what the user has picked
     * @param event 
     */
    @FXML
    public void checkEventChooseGender(Event event) {
        conditions[1] = true;
        if (maleBox.isSelected()) {
            characterGender = "Human";
            femaleBox.setSelected(false);
        } else if (femaleBox.isSelected()) {
            characterGender = "Pounder";
            maleBox.setSelected(false);
        }
    }
    /**Method
     * checks if user has picked any of the bodyType checkBoxes
     * pre:the user must have picked a bodyType checkBox
     * post: sets the character bodyType according to what the user has picked
     * @param event 
     */
    @FXML
    public void checkEventChooseBodyType(Event event) {
        conditions[2] = true;
        if (fatBox.isSelected()) {
            characterBodyType = "Fat";
            muscularBox.setSelected(false);
            slimBox.setSelected(false);
        } else if (muscularBox.isSelected()) {
            characterBodyType = "Muscular";
            fatBox.setSelected(false);
            slimBox.setSelected(false);
        } else if (slimBox.isSelected()) {
            characterBodyType = "Slim";
            fatBox.setSelected(false);
            muscularBox.setSelected(false);
        }
    }
    
    /**Method
     * checks if user has picked any of the class checkBoxes
     * pre:the user must have picked a class checkBox
     * post: sets the character class according to what the user has picked
     * @param event 
     */
    @FXML
    public void checkEventChooseClass(Event event) {
        conditions[3] = true;
        if (warriorBox.isSelected()) {
            characterClass = "Warrior";
            archerBox.setSelected(false);
            knightBox.setSelected(false);
        } else if (archerBox.isSelected()) {
            characterClass = "Archer";
            knightBox.setSelected(false);
            warriorBox.setSelected(false);
        } else if (knightBox.isSelected()) {
            characterClass = "Knight";
            archerBox.setSelected(false);
            warriorBox.setSelected(false);
        }
    }
    /**Method
     * Checks if the user has finished all the categories  required, and opens up a new scene accordingly
     * pre:the user must have clicked the done button
     * post:changes the scene if the user has clicked and done all the categories required
     * @param event
     * @throws Exception 
     */
    @FXML
    public void doneButton(Event event) throws Exception {
        int adder = 0;
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] == true) {
                adder++;
            }
        }
        if (adder == 5) {
            String FxName = "mainGameScreen.fxml";
            Stage stage = (Stage) doneButton.getScene().getWindow();
            MakingGame newGame = new MakingGame();
            newGame.start(stage, FxName);
        } else{
            description.setText("Please check all boxes and enter a name thats valid!");
        }
    }

}

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
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GameScreenController implements Initializable {

    @FXML
    public Label characterLabel, description;
    @FXML
    public TextArea textBox;
    @FXML
    public TextField characterNameTextField;
    @FXML
    public CheckBox humanBox, pounderBox, maleBox, femaleBox, slimBox, fatBox, muscularBox, warriorBox, archerBox, knightBox;

    private ArrayList<String> allDescriptions = new ArrayList();
    private String characterName, characterRace, characterGender, characterClass, characterBodyType;
    private MakeTextFile newFile;
    private boolean[] conditions = {false, false, false, false};

    /**
     * Initializes the controller class.
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

        newFile = new MakeTextFile("C:/Users/USER/Documents/limo.txt");
        try {
            newFile.createNewFile();
            newFile.createInputStream();
        } catch (FileNotFoundException ex) {
        }

        textBox.setEditable(false);
        textBox.setText("Welcome Player!" + "\n");
        setTimer();

    }

    public void setTimer() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> displayGuide()));
        timeline.setCycleCount(6);
        timeline.play();

    }

    public void displayGuide() {
        try {
            textBox.appendText(newFile.OutPutReadLine() + "\n");

        } catch (IOException ex) {
            System.err.print("Error Found");
        }
    }

    public void EnteredCharacterName(ActionEvent event) {

        if (characterNameTextField.getText() != null) {
            characterLabel.setText("Your Character Name Can't Be Nothing");
        } else {
            characterName = characterNameTextField.getText();
            characterLabel.setText("Your Character Name is: " + characterName);
        }
    }

    public void warriorDescription(Event eve) {
        description.setText(allDescriptions.get(0));
    }

    public void archerDescription(Event event1) {
        description.setText(allDescriptions.get(1));
    }

    public void knightDescription(Event event) {
        description.setText(allDescriptions.get(2));
    }

    public void humanDescription(Event event) {
        description.setText(allDescriptions.get(3));
    }

    public void pounderDescription(Event event) {
        description.setText(allDescriptions.get(4));
    }

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

    public void checkEventChooseBodyType(Event event) {
        conditions[2] = true;
        if (fatBox.isSelected()) {
            characterBodyType = "Fat";
            muscularBox.setSelected(false);
            slimBox.setSelected(false);
        } else if (muscularBox.isSelected()) {
            characterGender = "Muscular";
            fatBox.setSelected(false);
            slimBox.setSelected(false);
        } else if (slimBox.isSelected()) {
            characterGender = "Slim";
            fatBox.setSelected(false);
            muscularBox.setSelected(false);
        }
    }

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

    public void exit(Event l) {
        description.setText("e");
    }

}

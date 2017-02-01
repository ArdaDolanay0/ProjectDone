/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import changeLineInFile.ChangeLine;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainGameScreenController implements Initializable {

    @FXML
    private Button confirmButton;
    private ChangeLine newChanges;

    /**Method
     * creates dialogue options
     * pre:none
     * post:calles the createDialogueOptions method
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            createDialogueOptions();
        } catch (IOException ex) {

        }
    }
    /**Method
     * sets a local instance object and calles  modifyNarratorDialogue1
     * pre:none
     * post: newChanges has been in initialize and called modifyNarratorDialogue1 
     * @throws IOException 
     */
    public void createDialogueOptions() throws IOException {
        newChanges = new ChangeLine();
        modifyNarratorDialogue1();
    }
    /**Method
     *Changes certain lines in the naratorDialuge1 file
     * pre:none
     * post:Lines have been changed in the naratorDialuge1 file
     * @throws IOException 
     */
    public void modifyNarratorDialogue1() throws IOException {
        String file = "src/files/naratorDialuge1.txt";
        String word1 = GameScreenController.characterClass;
        String word2 = GameScreenController.characterName;
        String word3 = GameScreenController.characterBodyType;
        newChanges.changeALineInATextFile(file, word1+"'s Squire", 4);
        newChanges.changeALineInATextFile(file, word2, 6);
        newChanges.changeALineInATextFile(file, word3, 8);
    }
    /**Method
     * Changes the currant scene to another one
     *pre:confirm button must have been clicked
     * post:The scene has been changed
     * @param event
     * @throws Exception 
     */
    public void confirmButtonAction(ActionEvent event) throws Exception {
        String FxName = "storyScreen.fxml";
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        MakingGame newGame = new MakingGame();
        newGame.start(stage, FxName);
    }

}

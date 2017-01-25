/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import Dialogues.Dialogue;
import File.MakeMusicFile;
import File.MakeTextFile;
import LinkList.LinkList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.animation.Animation.Status.STOPPED;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StoryScreenController implements Initializable {

    @FXML
    private TextArea mainTextArea;
    @FXML
    private ComboBox<?> characterComboBox;
    @FXML
    private Button inventoryButton;
    @FXML
    private TreeView<?> equipmentTreeView;
    @FXML
    private ListView<String> commandsListView;
    @FXML
    private Button saveButton;
    @FXML
    private MediaView mv;

    private MediaPlayer mp;
    private MakeMusicFile newMusic;
    private Media me;
    private MakeTextFile newTextFile;
    private int time;
    private Timeline timeline;
    private boolean running, condition = true, conditionListner = true, nextQuestionCondition;
    private Thread thread;
    private int chapter = 0;
    private int nextQuestion, nextQuestionSeter;
    private String answer;
    private String[] commands, commandsText;
    private LinkList itemsNotInInventory;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemsNotInInventory = new LinkList();
        running = true;
        thread = new Thread(new game());
        thread.start();
        playMusic();

        //startGame();
        //startDialuge();
    }

    private void scene1(int time) {
        switch (chapter) {
            case 0:

                mainTextArea.setEditable(false);
                if (condition) {
                    mainTextArea.appendText("Act 1, Scene 1 \n");
                    condition = false;
                }
                chapter = 1;

                break;
            case 1:
                if (!condition) {
                    //System.out.println("no");
                    dialuge1();
                    condition = true;
                }
                chapter = 2;
                break;
            case 2:
                if (condition && timeline.getStatus().equals(STOPPED)) {
                    time = nextQuestion;
                    squireDialuge(nextQuestion);
                    condition = false;
                    chapter = 3;
                }

                break;
            case 3:
                if (!condition) {
                    //commandsListView.getItems().clear();
                    time = nextQuestion;
                    displayUserAnswer(nextQuestion);
                    condition = true;
                    chapter = 4;
                    //squireDialuge(nextQuestion);
                }
                break;
            case 4:
                if (condition) {
                    if (nextQuestionCondition) {
                        time = nextQuestion;
                        //commandsListView.getItems().clear();
                        squireDialuge(nextQuestion);
                        condition = false;
                        nextQuestionCondition = false;
                         chapter = 5;
                    }
                }
                break;
            case 5:
                if(!condition){
                    //changeDialuge();
                    //dialuge1();
                    addItem("Swort Sword",20);
                }

        }
    }
    public void addItem(String item, int damage ){
         itemsNotInInventory.InsertNextLink(item, damage);
    }

    public void startDialuge() {
        newTextFile = new MakeTextFile("src/files/naratorDialuge1.txt");
        newTextFile.createNewFile();
        try {
            newTextFile.createInputStream();
        } catch (FileNotFoundException ex) {

        }
    }

    public void dialuge1() {

        int cycle = 9;
        setTimer(cycle);

    }

    public void squireDialuge(int i) {
        String[] squireDialuge = {"Hey " + GameScreenController.characterName + " aren't you exited for the war? \n", "\n it's gonna be fun, \n you seem very nice here a sword for your honours.", "Yea it's going to be fun"};
        mainTextArea.appendText(squireDialuge[i]);
        String[][] avaibleCommands = {{"Say yes", "Say no", "ignore"}, {"Say yes", "Say no", "ignore"}};
        commands = avaibleCommands[i];
        String[][] textCommends = {{"yes", "no", ""}, {"yes", "no", ""}};
        commandsText = textCommends[i];
        //avaliableCommandsDialuge(avaibleCommands[i], i, textComments[i]);
    }

    public void displayUserAnswer(int i) {
        commandsListView.getItems().addAll(commands);
 
        ChangeListener<String> listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (conditionListner) {
                    nextQuestion = commandsListView.getItems().indexOf(newValue) + 1;
                    newValue = commandsText[commandsListView.getItems().indexOf(newValue)];
                    mainTextArea.appendText(newValue);
                    conditionListner = false;
                    nextQuestionCondition = true;
                }

            }
        };
        commandsListView.getSelectionModel().selectedItemProperty().addListener(listener);
        if(nextQuestionCondition ){
          commandsListView.getSelectionModel().selectedItemProperty().removeListener(listener);
        }
    }
    

    public void setTimer(int cycle) {

        time = 0;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(100),
                e -> displayWords()));
        timeline.setCycleCount(cycle);
        timeline.play();
        //squireDialuge();

    }

    public void startGame() {
        int chapters = 0, time = 0;
        startDialuge();
        while (running) {
            act1(time);
        }
        stop();
    }

    public void act1(int time) {

        scene1(time);
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(StoryScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayWords() {
        //time++;

        try {
            mainTextArea.appendText(newTextFile.OutPutReadLine() + "\n");

        } catch (IOException ex) {
            System.err.print("Error Found");
        }
    }

    public void playMusic() {

        String path = new File("src/media/song2.mp3").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);

        /*newMusic = new MakeMusicFile("C:/Users/USER/Documents/NetBeansProjects/MainProject/src/media/song2.mp3",mp);
         try {
         newMusic.createNewFile();
         newMusic.createMusicPlayer();
         } catch (FileNotFoundException ex) {
         }
         mv.setMediaPlayer(newMusic.returnMediaPlayer());
         (newMusic.returnMediaPlayer()).play();*/
    }
    
    public void inventoryButton(ActionEvent event) throws IOException{
         Parent root;
         root = FXMLLoader.load(getClass().getClassLoader().getResource("inventoryScreen.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();
    }

    class game implements Runnable {

        @Override
        public void run() {
            startGame();
        }
        /*
         public void startGame(){
         while(true){
         act1();
         }
         }
         public void act1(){
         int chapters = 0;
         scene1(chapters);
         }*/
    }

}

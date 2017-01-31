/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;


import File.MakeTextFile;
import Items.Items;
import LinkList.LinkList;
import Swords.Swords;
import classOptions.characterTypes;
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
import javafx.collections.FXCollections;
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
    private ComboBox<String> characterComboBox;
    @FXML
    private Button inventoryButton;
    @FXML
    private TreeView<?> equipmentTreeView;
    @FXML
    private ListView<String> commandsListView;
    @FXML
    private MediaView mv;

    private MediaPlayer mp;
    private Media me;
    private MakeTextFile newTextFile;
    private int time;
    private Timeline timeline;
    private boolean condition = true, conditionListner = true, nextQuestionCondition = false;
    private volatile boolean running;
    private Thread thread;
    private int chapter = 0;
    private int nextQuestion, characterConditions = 0;
    private String[] commands, commandsText;
    private characterTypes types;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] textCommends = {"yes", "no", ""};
        commandsText = textCommends;
        String[] avaibleCommands = {"Say yes", "Say no", "ignore"};
        commands = avaibleCommands;
        commandsListView.getItems().addAll(commands);
        commandsListView.setVisible(false);
        types = new characterTypes(GameScreenController.characterRace, GameScreenController.characterClass, GameScreenController.characterBodyType, GameScreenController.characterRace);
        newTextFile = new MakeTextFile("");
        running = true;
        thread = new Thread(new game());
        thread.setDaemon(true);
        thread.start();
        playMusic();

        //startGame();
        //startDialuge();
    }

    private void scene1() {
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
                    startDialuge("src/files/naratorDialuge1.txt");
                    dialuge(9);
                    condition = true;
                }
                chapter = 2;
                break;
            case 2:
                if (condition && timeline.getStatus().equals(STOPPED)) {
                    squireDialuge(nextQuestion);
                    condition = false;
                    chapter = 3;
                }

                break;
            case 3:
                if (!condition) {

                    commandsListView.setVisible(true);
                    displayUserAnswer(nextQuestion);
                    chapter = 4;
                    condition = true;

                }
                break;
            case 4:
                if (condition) {
                    if (nextQuestionCondition) {

                        commandsListView.setVisible(false);
                        squireDialuge(nextQuestion);
                        condition = false;
                        nextQuestionCondition = false;

                        chapter = 5 + (nextQuestion - 1);
                    }
                }
                break;
            case 5:
                if (!condition) {

                    startDialuge("src/files/naratorDialuge2.txt");
                    dialuge(3);
                    chapter = 8;
                    condition = true;

                }
                break;
            case 6:
                if (!condition) {
                    startDialuge("src/files/naratorDialuge3.txt");
                    dialuge(3);
                    chapter = 8;
                    condition = true;

                }
                break;
            case 7:
                if (!condition) {
                    startDialuge("src/files/naratorDialuge3.txt");
                    dialuge(4);
                    chapter = 8;
                    condition = true;
                }
                break;

            case 8:
                if (condition && timeline.getStatus().equals(STOPPED)) {
                    startDialuge("src/files/naratorDialuge4.txt");
                    dialuge(4);
                    chapter = 9;
                    condition = false;
                }
                break;
            case 9:
                if (!condition) {
                    addCharactersToComboBox();
                    condition = true;
                }
                break;
            case 10:
                if (condition) {
                    nextQuestion = 0;
                    characterComboBox.setVisible(false);
                    startJeffDialuge(nextQuestion);
                    chapter = 13;
                    condition = false;
                }
                break;
            case 11:
                if (condition) {
                    nextQuestion = 0;
                    characterComboBox.setVisible(false);
                    startGrogDialuge(nextQuestion);
                    condition = false;
                    chapter = -1;
                }
                break;
            case -1:
                if (!condition) {
                    conditionListner = true;
                    commandsListView.getSelectionModel().select(-1);
                    commandsListView.setVisible(true);
                    chapter = -2;
                    condition = true;
                }
                break;
            case -2:
                if (condition) {
                    if (nextQuestionCondition) {
                        commandsListView.setVisible(false);
                        startGrogDialuge(nextQuestion);

                        characterConditions++;

                        //characterComboBox.getItems().remove(1);
                        chapter = 15;
                        condition = false;
                        nextQuestionCondition = false;
                    }
                }

                break;
            case 13:
                if (!condition) {
                    conditionListner = true;
                    commandsListView.getSelectionModel().select(-1);//gives an error yet allows the item that the user clicked to be unselected.9+[-0-           ok,
                    commandsListView.setVisible(true);
                    chapter = 14;

                    condition = true;
                }
                break;
            case 14:
                if (condition) {
                    if (nextQuestionCondition) {
                        commandsListView.setVisible(false);
                        startJeffDialuge(nextQuestion);
                        condition = false;
                        nextQuestionCondition = false;
                        //characterComboBox.getItems().remove(0);
                        characterConditions++;
                        chapter = 15;
                    }
                }
            case 15:
                if (!condition) {
                    characterComboBox.setVisible(true);
                    if (characterConditions == 2) {
                        startDialuge("src/files/naratorDialuge5.txt");
                        dialuge(6);
                        chapter = 16;
                        condition = true;
                    }
                    //if (characterComboBox.getItems().isEmpty()) {

                    //}
                    condition = true;
                }
                break;
            case 16:
                if (condition) {
                    System.out.println("yes");
                    conditionListner = true;
                    commandsListView.getSelectionModel().select(-1);
                    commandsListView.setVisible(true);

                    condition = false;
                    chapter = 17;
                }
                break;
            case 17:
                if (!condition) {
                    if (nextQuestionCondition) {
                        commandsListView.setVisible(false);
                        startKingDialuge(nextQuestion);
                        condition = true;
                        nextQuestionCondition = false;
                    }
                }
                break;
            case 18:
                if (condition) {
                    startDialuge("src/files/naratorDialuge6.txt");
                    dialuge(3);
                    addKingsVictims();
                    condition = false;
                }
                break;
            case 19:
                if (!condition) {
                    mainTextArea.appendText("\nYou are trown out of the kigdom\n");
                    chapter = 22;
                    condition = true;
                }
                break;
            case 20:
                if (condition) {
                    mainTextArea.appendText("\n You have prosecuted an innocent man\n");
                    chapter = 24;
                    condition = false;
                }
                break;
            case 21:
                if (!condition) {
                    mainTextArea.appendText("\n Somehow you found the traitor\n");
                    chapter = 22;
                    condition = true;
                }
                break;
            case 22:
                if (condition) {
                    mainTextArea.appendText("To be continued in happlity Act 2");
                    running = false;
                    stop();
                }
                break;
            case 23:
                if (condition) {
                    mainTextArea.appendText("\nThe feast is ambused in the night.\n");
                    chapter = 24;
                    condition = false;
                }
                break;
            case 24:
                if (!condition) {
                    mainTextArea.appendText("To be continued in Act 2");
                    running = false;
                    stop();
                }

        }
    }

    private void addKingsVictims() {
        ObservableList<String> list = FXCollections.observableArrayList("Jeff", "Grog", "Doctor", "Billy the Squire", "Sqastroplinotikosmarinapazlini");
        characterComboBox.setItems(list);
    }

    private void startKingDialuge(int i) {
        String[] kingDialuge = {"", "\n You shall be my judge and jugery pick any of these guilty criminals!\n ", "\n How dare you disrespect your king throw this peasent out of my kingdom now!\n ", "\n Well seems like nobody is intrested, i shall take my leave\n "};
        mainTextArea.appendText(kingDialuge[i]);
        if (i == 1) {
            chapter = 18;
        } else if (i == 2) {
            chapter = 19;
        } else if (i == 3) {
            chapter = 23;
        }
    }

    private void startGrogDialuge(int i) {
        String[] grogDialuge = {"Hello " + GameScreenController.characterName + " I presume you require my assitence? \n", "\n Good to know that there are reliable men like you around, to be seen later\n",
            "\n Well not everyone has clear mind in this world, to be seen later \n", "\n Well I was shocked myself from this news as well, to be seen later young child \n"};
        String[] grogBodyType = {"You seem pretty strong, boy, I must tell you my news \n there is a traitor among us.", "Muffins can make a man bold, but your condition is rather perculier", "You have much to learn child, first off listen to your mother for advise."};

        mainTextArea.appendText(grogDialuge[i]);
        if (i == 0) {
            mainTextArea.appendText(grogBodyType[types.getBodyType()]);
        }
    }

    private void startJeffDialuge(int i) {
        String[] JeffDialuge = {"Hey " + GameScreenController.characterName + " fech me some win will ye \n", "\n Good, good are we, ahahaha \n Well what cha waitin for...\n uhhhh, well I don ned u an e longur, get lost will ya\n",
            "\n And I thought your were cool, what cha show off egh\n", "\n Huh, i'll report this to the king egh", "kllklk"};
        String[] JeffGender = {"you gotta nice lookin behind, there sweety \n ahhahahhahahahaahaha\n", ""};
        String[] JeffBodyType = {"i could make a fine henchmen off ya", "loose some wieght will u also\n ahahahahahaha", "eat more beaf will ya, egh!"};

        mainTextArea.appendText(JeffDialuge[i]);
        if (i == 0) {
            mainTextArea.appendText(JeffGender[types.getGender()]);
        }
        if (types.getGender() == 1) {
            mainTextArea.appendText(JeffBodyType[types.getBodyType() + i]);
        }
    }

    private void addCharactersToComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList("Sir Jeff", "Maester Grog");
        characterComboBox.setItems(list);
    }

    public void startDialuge(String fileName) {
        newTextFile.changeFileName(fileName);
        newTextFile.createNewFile();
        try {
            newTextFile.createInputStream();
        } catch (FileNotFoundException ex) {

        }
    }

    public void dialuge(int cycle) {

        setTimer(cycle);

    }

    public void squireDialuge(int i) {
        String[] squireDialuge = {"Hey " + GameScreenController.characterName + " aren't you exited for the war? \n", "\n it's gonna be fun, \n you seem very nice here a sword for your honours.\n", "\n And I thought your were cool\n", "\n huh whatever \n"};
        mainTextArea.appendText(squireDialuge[i]);

        //avaliableCommandsDialuge(avaibleCommands[i], i, textComments[i]);
    }

    private final ChangeListener<String> listener = new ChangeListener<String>() {
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

    public void displayUserAnswer(int i) {

        commandsListView.getSelectionModel().selectedItemProperty().addListener(listener);
        if (nextQuestionCondition) {
            System.out.println("Fuck");

        }
    }

    public void setTimer(int cycle) {

        time = 0;
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1500),
                e -> displayWords()));
        timeline.setCycleCount(cycle);
        timeline.play();
        //squireDialuge();

    }

    public void startGame() {
        while (running) {
            act1();
        }
        stop();
    }

    public void act1() {

        scene1();
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

    public void comboChanged(ActionEvent event) {
        switch (characterComboBox.getValue()) {
            case "Sir Jeff":
                chapter = 10;
                break;
            case "Maester Grog":
                chapter = 11;
                break;

        }
        if (characterComboBox.getValue().equalsIgnoreCase("Jeff") || characterComboBox.getValue().equalsIgnoreCase("Billy the Squire") || characterComboBox.getValue().equalsIgnoreCase("Doctor") || characterComboBox.getValue().equalsIgnoreCase("Sqastroplinotikosmarinapazlini")) {
            chapter = 20;
        } else if (characterComboBox.getValue().equalsIgnoreCase("Grog")) {
            chapter = 21;
        }

    }

    class game implements Runnable {

        @Override
        public void run() {
            startGame();
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainScreenController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView mainBackground;
    @FXML
    private ImageView secondImage;
    @FXML
    private ImageView mainTitle;
    @FXML
    private MediaView mv;
    @FXML
    private MediaPlayer mp;
    private Media me;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String path = new File("src/media/song.mp3").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.play();
       changeBackgroundColour(mainBackground);
       changeBackgroundColour(secondImage);
    }

    public void startButton(ActionEvent event) throws Exception {
        //Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        //Stage primaryStage = new Stage();
        // stage.close();
        mp.dispose();

        String FxName = "gameScreen.fxml";
        MakingGame newGame = new MakingGame();
        newGame.start(stage, FxName);
    }

    public void changeBackgroundColour(ImageView view) {
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.5);
        
        view.setEffect(blackout);
        view.setCache(true);
        view.setCacheHint(CacheHint.SPEED);
    }

}

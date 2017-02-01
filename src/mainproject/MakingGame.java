/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**Class
 *creates new scenes for fx documents
 * pre:none
 * post created new scene
 * @author test
 */
public class MakingGame  {
   
    
    /*public MakingGame(Stage stage){
        this.stage = stage;
    }*/

   /**Method
    * creates a new scene in the fx document
    * pre:none
    * post:created a new scene 
    * @param primaryStage
    * @param fxName
    * @throws Exception 
    */
    public void start(Stage primaryStage, String fxName) throws Exception {
        
         Parent root = FXMLLoader.load(getClass().getResource(fxName));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainproject;

import Items.Items;
import LinkList.LinkList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InventoryScreenController implements Initializable {

    @FXML
    private TableView<Items> table;
    @FXML
    private Button equipButton;
    @FXML
    private Button sortButton;
    @FXML
    private TableColumn<Items, String> name;
    @FXML
    private TableColumn<Items, Integer> value;

    public final ObservableList<Items> list = FXCollections.observableArrayList(new Items("huhhh",10));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setCellValueFactory(new PropertyValueFactory<Items, String>("Name"));
        value.setCellValueFactory(new PropertyValueFactory<Items, Integer>("Value"));
        
        table.setItems(list);

    }
    //public ObservableList<Items> list = FXCollections.observableArrayList(

    //)
}

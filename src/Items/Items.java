/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class Items {

   private final SimpleStringProperty name;
   private final SimpleIntegerProperty value;

   public Items(String name, int value) {
      this.name = new SimpleStringProperty(name);
      this.value = new SimpleIntegerProperty(value);
   }

   public StringProperty nameProperty() { return name; }
   public IntegerProperty valueProperty() { return value; }
}

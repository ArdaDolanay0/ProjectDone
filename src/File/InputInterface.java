/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.FileNotFoundException;
import java.io.IOException;

/**Class
 * this is the input interface for the file 
 * pre: inputInterface must be implemented 
 * post: initializes input methods
 */
public interface InputInterface {
    
    public void createInputStream()throws FileNotFoundException;
     public String OutPutReadLine()throws IOException;
}

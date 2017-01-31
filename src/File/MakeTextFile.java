/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author USER
 */
public class MakeTextFile extends MakeFile implements InputInterface, OutputInterface {
     private StringBuilder out;
     private BufferedReader buffRead;
     private InputStream fis;
     private FileReader reader;
     private BufferedWriter buffWriter;
     private OutputStreamWriter output;
     private FileOutputStream fileOut;
     private File file;
     private String fileName;
    public MakeTextFile(String fileName) {
        super(fileName);
        this.fileName = getFileName();
    }
    @Override
    public void createNewFile(){
        file = new File(fileName);
    }
    
     @Override
    public void createInputStream() throws FileNotFoundException{
         
        fis = new FileInputStream(file);
        buffRead = new BufferedReader(new InputStreamReader(fis));
        out = new StringBuilder();
    
    }
    
     @Override
    public String OutPutReadLine() throws IOException{
        String nextLine;
         if ( (nextLine =buffRead.readLine()) != null) 
               return nextLine;
         else
             return null;
             
         
    }
    
    public File returnFile(){
        return file;
    }
    
    public void createFileReader() throws FileNotFoundException{
        reader = new FileReader(file);
    }
    public FileReader returnFileReader(){
        return reader;
    }

    @Override
    public void createOutputStream() throws FileNotFoundException, UnsupportedEncodingException {
       //buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(textFile), "utf-8")); 
        fileOut = new FileOutputStream(file);
         output = new OutputStreamWriter(fileOut, "utf-8");
         buffWriter = new BufferedWriter(output);
    }
    public BufferedWriter returnBuffer() throws IOException{
         return buffWriter;
    }
    
    public void changeFileName(String name){
        fileName = name;
    }
    
}

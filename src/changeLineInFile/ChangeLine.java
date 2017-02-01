/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changeLineInFile;
import File.MakeTextFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ChangeLine {

    private MakeTextFile newTextFile;

    /*Method
     *changes a line in a text file
     *pre: none
     *post: a line in a text file has been changed
     */
    public void changeALineInATextFile(String fileName, String newLine, int lineNumber) throws UnsupportedEncodingException, IOException {
        newTextFile = new MakeTextFile(fileName);
        String content = new String();
        String editedContent = new String();
        content = readFile(fileName);
        editedContent = editLineInContent(content, newLine, lineNumber);
        writeToFile(fileName, editedContent);

    }
    /**Method
     * returns the number of lines in a file
     * pre:must be called from editLineContent
     * post:a line in the text file has been changed
     * @param content
     * @return 
     */
    private int numberOfLinesInFile(String content) {
        int numberOfLines = 0;
        int index = 0;
        int lastIndex = 0;
        //sets lastIndex to the files lenght
        lastIndex = content.length() - 1;

        while (true) {
            //whenever a line has a space increases the number of lines
            if (content.charAt(index) == '\n') {
                numberOfLines++;

            }
           //when the index has reached the lastIndex exit the while loop and increase the numberOfLines by one
            if (index == lastIndex) {
                numberOfLines = numberOfLines + 1;
                break;
            }
            index++;

        }

        return numberOfLines;
    }
    /**Method
     * turns a file into a string array
     * pre: number of lines must be calculated
     * post:the file becomes a String Array
     * @param content
     * @param lines
     * @return 
     */
    private String[] turnFileIntoArrayOfStrings(String content, int lines) {
        String[] array = new String[lines];
        int index = 0;
        int tempInt = 0;
        int startIndext = 0;
        int lastIndex = content.length() - 1;

        while (true) {
             //whenever a line in the file have space add the components in the file to a String array
            if (content.charAt(index) == '\n') {
                tempInt++;

                String temp2 = new String();
                for (int i = 0; i < index - startIndext; i++) {
                    temp2 += content.charAt(startIndext + i);
                }
                startIndext = index;
                array[tempInt - 1] = temp2;

            }
            //Whenever index has reaches the last index, add the components in the file to a String array, and end while loop
            if (index == lastIndex) {

                tempInt++;

                String temp2 = new String();
                for (int i = 0; i < index - startIndext + 1; i++) {
                    temp2 += content.charAt(startIndext + i);
                }
                array[tempInt - 1] = temp2;

                break;
            }
            index++;

        }

        return array;
    }
    /**Method
     * edits a line in the file
     * pre:none
     * post: the line in the file has been changed
     * @param content
     * @param newLine
     * @param line
     * @return 
     */
    private String editLineInContent(String content, String newLine, int line) {

        int lineNumber = 0;
        lineNumber = numberOfLinesInFile(content);

        String[] lines = new String[lineNumber];
        lines = turnFileIntoArrayOfStrings(content, lineNumber);
        //if line number is not one,  it make space put the new line
        if (line != 1) {
            lines[line - 1] = "\n" + newLine;
        //if it is put the new line into it
        } else {
            lines[line - 1] = newLine;
        }
        content = new String();
        //add the edite dlines to the content
        for (int i = 0; i < lineNumber; i++) {
            content += lines[i];
        }

        return content;
    }
    /**Method
     * writes to file
     * pre:the file has to be been read from and the contents edited
     * post:the file has been written to
     * @param file
     * @param content
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    private void writeToFile(String file, String content) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        newTextFile.createOutputStream();
      
        try {
            newTextFile.returnBuffer().write(content);
        } finally {
            if (newTextFile.returnBuffer() != null) {
                newTextFile.returnBuffer().close();
            }
        }
      
    }
    /**Method
     * reads from file
     * pre:none
     * post:the file has been read from
     * @param filename
     * @return 
     */
    private String readFile(String filename) {
        String content = null;

        try {
            newTextFile.createNewFile();
            newTextFile.createFileReader();
            
            char[] chars = new char[(int) newTextFile.returnFile().length()];
            newTextFile.returnFileReader().read(chars);
           
            content = new String(chars);
            newTextFile.returnFileReader().close();
      
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (newTextFile.returnFileReader() != null) {
                try {
                    newTextFile.returnFileReader().close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println(content);
        return content;
    }

}

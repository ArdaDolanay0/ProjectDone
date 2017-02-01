/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classOptions;

/**
 *
 * @author USER
 */
public class characterTypes {
    //varaible declaration
    private int genderInt;
    private int cClassInt;
    private int bodyTypeInt;
    private int raceInt;
    private String gender;
    private String cClass;
    private String bodyType;
    private String race;
    
    /**Constructor
     * Initializes all the character variables the user picked to local class variables
     * pre:none
     * post:All the String instance variables have been set to user choices
     * pre:none
     * post:
     * @param gender
     * @param cClass
     * @param bodyType
     * @param race 
     */
    public characterTypes(String gender, String cClass, String bodyType, String race){
        this.gender = gender;
        this.cClass = cClass;
        this.bodyType = bodyType;
        this.race = race;
    }
    /**Method
     * sets the genderInt Instance variable corresponding to player's chosen gender
     * pre:none
     * post:the genderInt has been set
     * @return 
     */
    public int getGender(){
        if(gender.equalsIgnoreCase("Male")){
            genderInt=0;
        }else if(gender.equalsIgnoreCase("Female")){
            genderInt=1;
        }
        return genderInt;
    }
    /**Method
     * sets the bodyType Instance variable corresponding to player's chosen gender
     * pre:none
     * post:the bodyType has been set
     * @return 
     */
    public int getBodyType(){
        if(bodyType.equalsIgnoreCase("Muscular")){
             bodyTypeInt = 0;
        }else if(bodyType.equalsIgnoreCase("Fat")){
             bodyTypeInt = 1;
        }else{
            bodyTypeInt = 2;
        }
        return bodyTypeInt;
    }
    
}

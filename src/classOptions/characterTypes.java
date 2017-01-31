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
    private int genderInt;
    private int cClassInt;
    private int bodyTypeInt;
    private int raceInt;
    private String gender;
    private String cClass;
    private String bodyType;
    private String race;
    public characterTypes(String gender, String cClass, String bodyType, String race){
        this.gender = gender;
        this.cClass = cClass;
        this.bodyType = bodyType;
        this.race = race;
    }
    public int getGender(){
        if(gender.equalsIgnoreCase("Male")){
            genderInt=0;
        }else if(gender.equalsIgnoreCase("Female")){
            genderInt=1;
        }
        return genderInt;
    }
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

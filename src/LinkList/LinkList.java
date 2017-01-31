/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkList;

/**
 *
 * @author USER
 */
class Link {
    private String swordName;
    private int sowrdAttack;
    
    Link next;
    
    public Link(String swordName, int sowrdAttack){
        this.sowrdAttack = sowrdAttack;
        this.swordName = swordName;
    }
    public String getSowrdName(){
        return swordName;
    }
  
}
public class LinkList{
    private Link firstLink;
    
    public void InsertNextLink(String name, int attack){
        Link newLink = new Link(name, attack);
        newLink.next = firstLink;
        firstLink = newLink;
        
    }
    
    public Link find(String name){
        Link theLink = firstLink;
        while(theLink.getSowrdName() != name){
            if(theLink.next ==null){
                return null;
            }else{
               theLink = theLink.next;
            }
        }
        return theLink;
    }
}


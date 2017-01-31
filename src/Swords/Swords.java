/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Swords;

/**
 *
 * @author USER
 */
public class Swords {
    private String name;
    private int damage;
    public Swords(String name, int damage){
        this.name = name;
        this.damage = damage;
    }
    
    public String returnName(){
        return name;
    }
   public int returnDamage(){
       return damage;
   }
}

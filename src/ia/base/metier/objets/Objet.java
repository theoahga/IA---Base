/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.objets;

import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.metier.cases.Case;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public abstract class Objet {
    Case position;
    
    /**
     * Constructeur de la classe Objet
     * @param position
     */
    public Objet(Case position){
        this.position = position;
    }
    
    /**
     * Renvoie le type d'objet
     * @return
     */
    public abstract TypeObjet getType();
    
    /**
     * Renvoie si l'objet est bloquant
     * @return
     */
    public abstract boolean estBloquant();
    
    /**
     * Renvoie si l'objet est récoltable
     * @return
     */
    public boolean estRecoltable(){
        return false;
    }
    
    /**
     * Renvoie le coût de l'objet
     * @return
     */
    public int coutRecolte(){
        return -1;
    }

    /**
     * Renvoie la case où se trouve le l'objet
     * @return
     */
    public Case getPosition() {
        return position;
    }
    
    /**
     * Renvoie le loot d'un objet quand il est récolté
     * @return
     */
    public abstract HashMap<TypeRessource,Integer> getLoot();
}

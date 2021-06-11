/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 *
 * @author theo clere
 */
public abstract class Action {

    /**
     * Revoie le message de l'action
     * @return
     */
    public abstract String getMessage();
    
    /**
     * Renvoie le type d'action de l'action
     * @return
     */
    public abstract TypeAction getType();
    
    /**
     * Revoie le type de mouvement de l'action
     * @return
     */
    public abstract TypeMouvement getDirection();
    
    /**
     * Renvoie le type de ressource de l'action
     * @return
     */
    public abstract TypeRessource getTypeRessource();
}

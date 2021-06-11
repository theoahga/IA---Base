/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static metier.actions.TypeAction.CUEILLIR;

/**
 *
 * @author theo clere
 */
public class ActionCueillir extends Action{

    @Override
    public String getMessage() {
        return "COLLECT";
    }

    @Override
    public TypeAction getType() {
        return CUEILLIR;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
    
}

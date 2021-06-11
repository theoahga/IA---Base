/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static metier.actions.TypeAction.MOUVEMENT;

/**
 *
 * @author theo clere
 */
public class ActionMouvement extends Action{
    private TypeMouvement typemouvement;

    public ActionMouvement(TypeMouvement typemouvement) {
        this.typemouvement = typemouvement;
    }

    @Override
    public String getMessage() {
        return "MOVE|"+this.typemouvement;
    }

    @Override
    public TypeAction getType() {
        return MOUVEMENT;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.typemouvement;
    }
    
    
    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
}

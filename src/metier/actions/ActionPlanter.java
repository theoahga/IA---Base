/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static metier.actions.TypeAction.PLANTER;

/**
 *
 * @author theo clere
 */
public class ActionPlanter extends Action{

    private TypeRessource typeRessource;

    public ActionPlanter(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }
    
    @Override
    public String getMessage() {
        return "PLANT|"+this.typeRessource;
    }

    @Override
    public TypeAction getType() {
        return PLANTER;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return this.typeRessource;
    }
    
}

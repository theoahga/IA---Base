/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static metier.actions.TypeAction.RECOLTE;

/**
 *
 * @author theo clere
 */
public class ActionCouperArbre  extends Action{
    public TypeMouvement typemouvement;

    public ActionCouperArbre(TypeMouvement typemouvement) {
        this.typemouvement = typemouvement;
    }

    
    @Override
    public String getMessage() {
        return "HARVEST|"+this.typemouvement;
    }

    @Override
    public TypeAction getType() {
        return RECOLTE;
    }

    @Override
    public TypeMouvement getDirection() {
        TypeMouvement a = null;
        if (this.typemouvement != null){
            a = this.typemouvement;
        }
        return a;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
    
}

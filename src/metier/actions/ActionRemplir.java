/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static metier.actions.TypeAction.REMPLIR;

/**
 *
 * @author theo clere
 */
public class ActionRemplir extends Action{

    private TypeMouvement direction;

    /**
     * Constructeur de la classe
     * @param direction
     */
    public ActionRemplir(TypeMouvement direction) {
        this.direction = direction;
    }
    
    @Override
    public String getMessage() {
        return "FILL|"+this.direction;
    }

    @Override
    public TypeAction getType() {
        return REMPLIR;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.direction;
    }

    @Override
    public TypeRessource getTypeRessource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

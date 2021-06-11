/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.cases;

import ia.base.metier.carte.Coordonnee;
import static ia.base.metier.cases.TypeCase.TERRE;

/**
 *
 * @author theo clere
 */
public class CaseTerre extends Case {
    
    public CaseTerre(Coordonnee coordonnee) {
        super(coordonnee);
    }

    @Override
    public TypeCase getType() {
        return TERRE;
    }

    @Override
    public boolean estAccessible() {
        boolean b = true;
        
        if(this.getObjet()!= null){
            if(this.getObjet().estBloquant()){
                b = false;
            }
        }
        return b;
    }
    
}

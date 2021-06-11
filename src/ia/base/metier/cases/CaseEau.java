/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.cases;

import ia.base.metier.carte.Coordonnee;
import static ia.base.metier.cases.TypeCase.EAU;

/**
 *
 * @author theo clere
 */
public class CaseEau extends Case {
    
    public CaseEau(Coordonnee coordonnee) {
        super(coordonnee);
    }

    @Override
    public TypeCase getType() {
        return EAU;
        
    }

    @Override
    public boolean estAccessible() {
        return false;
    }
    
}

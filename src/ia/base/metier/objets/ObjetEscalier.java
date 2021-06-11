/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.objets;

import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.metier.cases.Case;
import static ia.base.metier.objets.TypeObjet.ESCALIER;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public class ObjetEscalier extends Objet{

    public ObjetEscalier(Case position) {
        super(position);
    }

    @Override
    public TypeObjet getType() {
        return ESCALIER;
    }

    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> a = new HashMap<>();
        return a;
    }
    
}

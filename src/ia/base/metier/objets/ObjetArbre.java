/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.objets;

import ia.base.metier.carte.ressources.TypeRessource;
import static ia.base.metier.carte.ressources.TypeRessource.BOIS;
import ia.base.metier.cases.Case;
import static ia.base.metier.objets.TypeObjet.ARBRE;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public class ObjetArbre extends Objet {

    public ObjetArbre(Case position) {
        super(position);
    }

    @Override
    public TypeObjet getType() {
        return ARBRE;
    }

    @Override
    public boolean estBloquant() {
        return false;
    }
    
    @Override
    public boolean estRecoltable(){
        return true;
    }
    
    @Override
    public int coutRecolte(){
        return 2;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> a = new HashMap<>();
        a.put(BOIS,12);
        return a;
    }
}

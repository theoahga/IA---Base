/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.objets;

import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.metier.cases.Case;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public class PlantePanais extends Plante{
    
    public PlantePanais(Case position) {
        super(position);
    }
    
    @Override
    public TypeObjet getType() {
        return TypeObjet.PANAIS;
    }
    
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> l = new HashMap<>();
        l.put(TypeRessource.PARSNIPMATURE, 2);
        return l;
    }

    @Override
    public boolean estMature() {
        boolean b = false;
        if(this.getAge() >= 13){
            b = true;
        }
        return b;
    }
    
}

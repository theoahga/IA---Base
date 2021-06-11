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
public class PlanteChouFleur extends Plante{
    
    public PlanteChouFleur(Case position) {
        super(position);
    }
    
    @Override
    public TypeObjet getType() {
        return TypeObjet.CHOUFLEUR;
    }
    
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> loot = new HashMap<>();
        loot.put(TypeRessource.CAULIFLOWERMATURE, 2);
        return loot;
    }

    @Override
    public boolean estMature() {
        boolean b = false;
        if(this.getAge() >=5){
            b = true;
        }
        return b;
    }
}

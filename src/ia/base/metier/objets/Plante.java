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
public abstract class Plante extends Objet {
    
    private boolean estArrosee;
    private int age;

    public Plante(Case position) {
        super(position);
        this.estArrosee = false;
        this.age = 0;
    }

    @Override
    public TypeObjet getType() {
        return null;
    }

    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        return null;
    }

    /**
     * Renvoie si la plante est arrosée
     * @return
     */
    public boolean isEstArrosee() {
        return estArrosee;
    }

    /**
     * Change l'attribut estArrosée avec le paramètre donné
     * @param estArrosee
     */
    public void setEstArrosee(boolean estArrosee) {
        this.estArrosee = estArrosee;
    }

    /**
     * Renvoie l'age de la plante
     * @return
     */
    protected int getAge() {
        return age;
    }
    
    
    public void grandir(){
        this.age += 1;
    }
    
    public abstract boolean estMature();
}

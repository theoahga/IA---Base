/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.objets;

import ia.base.metier.cases.Case;


/**
 *
 * @author theo clere
 */
public class FabriqueObjet {
    
    /**
     * Permet de créer un objet sur une case en fonction de sa lettre
     * @param position
     * @param lettre
     * @return
     */
    public static Objet creer(Case position, Character lettre){
        Objet o = null;
        switch(lettre){
            case 'A':
                o = new ObjetArbre(position);
                break;
            case 'M':
                o = new ObjetMaison(position);
                break;
            case 'D':
                o = new ObjetDepart(position);
                break;
            case 'S':
                o = new ObjetEscalier(position);
                break;
        } 
        position.setObjet(o);
        return o;
    }
    
    /**
     * Permet de créer une plante sur une case en fonction de sa lettre
     * @param position
     * @param type
     * @return
     */
    public static Plante creerPlante(Case position, TypeObjet type){
        Plante plante = null;
        switch(type){
            case PANAIS:
                plante = new PlantePanais(position);
                break;
            case CHOUFLEUR:
                plante = new PlanteChouFleur(position);
                break;
        }
        return plante;
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.objets.Objet;
import ia.base.metier.objets.ObjetArbre;
import ia.base.metier.objets.FabriqueObjet;

/**
 *
 * @author theo clere
 */
public class FabriqueCase {
    
    /**
     * Permet de créer une case en fonction de sa lettre et de ses coordonnees
     * @param coordonnee
     * @param lettre
     * @return
     */
    public static Case creer(Coordonnee coordonnee, Character lettre){
        Case c = null;
        switch(lettre){
            case 'H':
                c = new CaseHerbe(coordonnee);
                break;
            case 'T':
                c = new CaseTerre(coordonnee);
                break;
            case 'E':
                c = new CaseEau(coordonnee);
                break;
            case 'A':
                c = new CaseHerbe(coordonnee);
                break;
            case 'M':
                c = new CaseHerbe(coordonnee);
                break;
            case 'D':
                c = new CaseHerbe(coordonnee);
                break;
        } 
        c.setObjet(FabriqueObjet.creer(c, lettre));
        return c;
    }



}

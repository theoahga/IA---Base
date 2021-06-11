/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier;

import ia.base.metier.carte.Coordonnee;

/**
 * 
 * @author theo clere
 */
public class Joueur {
    private Coordonnee coordonnee;

    /**
     * Constructeur de la classe Joueur
     * @param coordonnee
     */
    public Joueur(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    /**
     * Renvoie les coordonnee du joueur
     * @return
     */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }
    
    /**
     * Déplace le joueur aux coordonnee passé en paramètre
     * @param mouvement
     */
    public void deplacer(TypeMouvement mouvement){
        this.coordonnee = this.coordonnee.getVoisin(mouvement);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.TypeMouvement.BOTTOM;
import static ia.base.metier.TypeMouvement.LEFT;
import static ia.base.metier.TypeMouvement.RIGHT;
import static ia.base.metier.TypeMouvement.TOP;

/**
 *
 * @author theo clere
 */
public class Coordonnee {
    int ligne;
    int colonne;
    
    /**
     * Constructeur de la classe Coordonnee
     * @param ligne
     * @param colonne
     */
    public Coordonnee(int ligne,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.ligne;
        hash = 47 * hash + this.colonne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnee other = (Coordonnee) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        return true;
    }
    
    /**
     * Renvoie les coordonnee voisine à ces coordonnee grâce une type de mouvement
     * @param mouvement
     * @return
     */
    public Coordonnee getVoisin(TypeMouvement mouvement){
        Coordonnee c = null;
        switch(mouvement){
            case TOP:
                c = new Coordonnee(this.ligne-1,this.colonne);
                break;
            case BOTTOM:
                c = new Coordonnee(this.ligne+1,this.colonne);
                break;
            case RIGHT:
                c = new Coordonnee(this.ligne,this.colonne+1);
                break;
            case LEFT:
                c = new Coordonnee(this.ligne,this.colonne-1);
                break;
        }
        return c;
    }
    
    /**
     * Renvoie le mouvement pour aller aux coordonne passées en paramètre
     * @param destination
     * @return
     */
    public TypeMouvement getMouvementPourAller(Coordonnee destination){
        int a = this.ligne - destination.ligne;
        int b = this.colonne - destination.colonne;
        
        TypeMouvement move = null ;
        
        if(a == 1){
            move = TOP;
        }else if(a == -1){
            move = BOTTOM;
        }
        
        if(b == 1){
            move = LEFT;
        }else if(b == -1){
            move = RIGHT;
        }
        
        return move;

    }
    
    @Override
    public String toString(){
        return "Coordonnées : ("+this.ligne+","+this.colonne+")";
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}

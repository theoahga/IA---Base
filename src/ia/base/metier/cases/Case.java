/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.cases;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.TypeMouvement.BOTTOM;
import static ia.base.metier.TypeMouvement.LEFT;
import static ia.base.metier.TypeMouvement.RIGHT;
import static ia.base.metier.TypeMouvement.TOP;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.objets.Objet;
import java.util.ArrayList;

/**
 *
 * @author theo clere
 */
public abstract class Case {
    private Coordonnee coordonnee;
    private Objet objet;
    private ArrayList<Case> voisins;
    
    /**
     * Constructeur de la classe Case
     * @param coordonnee
     */
    public Case(Coordonnee coordonnee){
        this.coordonnee = coordonnee;
        this.voisins = new ArrayList<>();
        
    }
    
    /**
     * Renvoie les coordonnee de la Case
     * @return
     */
    public Coordonnee getCoordonnee(){
        return this.coordonnee;
    }

    /**
     * Renvoie les voisins de la case
     * @return
     */
    public ArrayList<Case> getVoisins() {
        return voisins;
    }
    
    /**
     * Change l'objet de la case par celui passé en paramètre
     * @param objet
     */
    public void setObjet(Objet objet){
        this.objet = objet;
    }
    
    /**
     * Renvoie l'objet de la case
     * @return
     */
    public Objet getObjet(){
        return this.objet;
    }
    
    /**
     * Ajouter une case à la liste des voisins
     * @param voisin
     */
    public void ajouterVoisin(Case voisin){
        this.voisins.add(voisin);
    }

    /**
     * Renvoie le type de case
     * @return
     */
    public abstract TypeCase getType();
    
    /**
     * Renvoie si la case est accessible 
     * @return
     */
    public abstract boolean estAccessible();
    
    /**
     * Renvoie le mouvement à effectuer pour aller à la case passée en paramètre
     * @param arrivee
     * @return
     */
    public TypeMouvement getMouvementPourAller(Case arrivee){
        int a = this.coordonnee.getLigne() - arrivee.coordonnee.getLigne();
        int b = this.coordonnee.getColonne() - arrivee.coordonnee.getColonne();
        
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
}

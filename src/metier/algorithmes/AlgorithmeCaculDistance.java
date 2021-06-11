/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.cases.Case;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public abstract class AlgorithmeCaculDistance {
    private Carte carte;
    private HashMap<Case,Integer> distances;

    /**
     * Constructeur de la classe AlgorithmeCaculDistance
     * @param carte
     */
    public AlgorithmeCaculDistance(Carte carte) {
        this.carte = carte;
        this.distances = new HashMap<>();
    }

    /**
     * Renvoie la carte 
     * @return
     */
    protected Carte getCarte() {
        return carte;
    }
    
    /**
     * Détermine une nouvelle distance
     * @param position
     * @param valeur
     */
    protected void setDistance(Case position, int valeur){
        this.distances.put(position, valeur);
    }

    /**
     * Renvoie la distance à une case passée en paramètre
     * @param arrivee
     * @return
     */
    public Integer getDistance(Case arrivee){
        return this.distances.get(arrivee);
    }
    
    /**
     * Vide la listes des distances
     */
    protected void reinitialisationDistances(){
        this.distances.clear();
    }
    
    /**
     * Calcule tous les distances depuis la case donnée en paramètre
     * @param depart
     */
    public abstract void calculerDistancesDepuis(Case depart);
    
    /**
     * Renvoie la liste de mouvement du chemin le plus court pour aller à la case passée en paramètre
     * @param arrivee
     * @return
     */
    public abstract ArrayList<TypeMouvement> getChemin(Case arrivee);
}

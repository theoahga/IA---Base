/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.cases.Case;
import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author theo clere
 */
public class Dijkstra extends AlgorithmeCaculDistance{
    
    private HashMap<Case,Boolean> estVisite ;
    private HashMap<Case,Case> precedecesseur;
    private int infini;

    public Dijkstra(Carte carte) {
        super(carte);
        this.estVisite = new HashMap<>();
        this.precedecesseur = new HashMap<>();
        this.infini = 1 + 16 * carte.size()* carte.size();
    }

    /**
     * Renvoie le cout pour aller à la case passée en paramètre
     * @return
     */
    private int coutMouvementVers(Case destination){
        int a = 1;
        if(destination.getObjet() != null){
            if(destination.getObjet().estRecoltable()){
                a += destination.getObjet().coutRecolte();
            }
        }
        if(!destination.estAccessible()){
            a = this.infini;
        }
        return a;
        
    }
    
    
    /**
     * Initialise grâce à la case passée en paramètre
     */
    private void initialisation(Case depart){
        for(Case c : this.getCarte().getCases()){
            this.setDistance(c, infini);
            this.estVisite.put(c, false);
            this.precedecesseur.put(c, null);
        }
        this.setDistance(depart,0);
    }
    
    /**
     * Relachement de Dijkstra
     */
    private void relachement(Case a, Case b){
        if (this.getDistance(b)>this.getDistance(a)+coutMouvementVers(b)){
            this.setDistance(b,this.getDistance(a)+coutMouvementVers(b));
            this.precedecesseur.put(b, a);
        }
    }
    
    /**
     * Renvoie la case la plus proche  
     * @return
     */
    private Case getCaseLaPlusProche(){
        int distanceMin = this.infini;
        Case res = null;
        for(Case C : this.getCarte().getCases()){
            if(!this.estVisite.get(C) && this.getDistance(C) < distanceMin){
                distanceMin = this.getDistance(C);
                res = C;
            }
        }
        return res;
    }
    
    
    @Override
    public void calculerDistancesDepuis(Case depart) {
        initialisation(depart);
        Case caseLaPlusProche = getCaseLaPlusProche();
        while(caseLaPlusProche != null){
            this.estVisite.put(caseLaPlusProche, TRUE);
            for(Case c : caseLaPlusProche.getVoisins()){
                relachement(caseLaPlusProche, c);
            }
            caseLaPlusProche = getCaseLaPlusProche();
        }
    }

    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //initialisation
        ArrayList<TypeMouvement> res = new ArrayList<>();
        Case caseEnCours = arrivee;
        Case p = this.precedecesseur.get(arrivee);
        
        //Traitement de données
        while(p != null){
            res.add(p.getMouvementPourAller(caseEnCours));
            caseEnCours = p; 
            p = this.precedecesseur.get(p);
        }
        Collections.reverse(res);
        
        //return
        return res;
        
    }
    
}

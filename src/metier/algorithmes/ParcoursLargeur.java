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
import java.util.Collections;

/**
 *
 * @author theo clere
 */
public class ParcoursLargeur extends AlgorithmeCaculDistance{

    public ParcoursLargeur(Carte carte) {
        super(carte);
    }

    @Override
    public void calculerDistancesDepuis(Case depart) {
        //Remise à zero
        ArrayList<Case> aTraiter = new ArrayList<>(); 
        this.reinitialisationDistances();
        
        //initialisation
        aTraiter.add(depart);
        this.setDistance(depart, 0);
        
        //Calcul
        while(!aTraiter.isEmpty()){
            Case caseEnCours = aTraiter.get(0);
            aTraiter.remove(0);
                for(Case v : caseEnCours.getVoisins()){
                    if(this.getDistance(v) == null)
                        if(v.estAccessible()){
                            this.setDistance(v, this.getDistance(caseEnCours)+1);
                            aTraiter.add(v);
                        }
                }
        }
    }

    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //Initilisation
        ArrayList<TypeMouvement> resultat = new ArrayList<>();
        Case caseEnCours = arrivee;
        
        //Calcul
        if(caseEnCours != null){
            while(this.getDistance(caseEnCours)>0){
                Case casePrecedente = null;
                for(Case v : caseEnCours.getVoisins()){
                    if(getDistance(v) != null   &&   getDistance(v)==(getDistance(caseEnCours)-1)){
                        casePrecedente = v;
                    }
                }
                TypeMouvement move = casePrecedente.getMouvementPourAller(caseEnCours);   
                resultat.add(move);
                caseEnCours = casePrecedente;
            }
            Collections.reverse(resultat);
        }
        return resultat;
    }

    
}

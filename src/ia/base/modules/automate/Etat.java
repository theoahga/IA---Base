/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.cases.Case;
import java.util.List;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.algorithmes.AlgorithmeCaculDistance;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public abstract class Etat {
    private Automate automate;

    public Etat(Automate automate) {
        this.automate = automate;
    }
    
    /**
     * Transition de l'état
     * @return
     */
    public abstract Etat transition();
    
    /**
     * Action de l'état
     * @return
     */
    public abstract Action action();

    protected Automate getAutomate() {
        return automate;
    }
    
    /**
     * Permet de déplacer le joueur en suivant l'alogrithme de Dijkstra
     * @param coordonnee
     */
    protected void seDeplacerEn(Coordonnee coordonnee){
        System.out.println("--- Je veux aller en "+coordonnee+" ---");
        //On récupère la carte
        Carte carte = this.getAutomate().getModuleMemoire().getCarte();
        //On crée l'algo
        AlgorithmeCaculDistance algo = new Dijkstra(carte);
        //On récupère la case du joueur
        Case caseJoueur = this.automate.getModuleMemoire().getCaseJoueur();
        //On lance les calculs de distance
        algo.calculerDistancesDepuis(caseJoueur);
        //On détermine la case de destination
        Case destination = carte.getCase(coordonnee);
        //On calcule le chemin
        List<TypeMouvement> listeMouvement = algo.getChemin(destination);
        //On crée les actions
        for(TypeMouvement typeMouvement : listeMouvement) {
            this.automate.getListeDesActionArealiser().add(FabriqueAction.creerMouvement(typeMouvement));
        }
    }
    
}

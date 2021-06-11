/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.metier.cases.Case;
import ia.base.metier.cases.TypeCase;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public class EtatAllerPlanter extends Etat{
    private boolean aPlante = false;
    public EtatAllerPlanter(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        if(aPlante == true){
            etat = new EtatCheckAction(this.getAutomate());
        }else{
            etat = new EtatAllerArroser(this.getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        if(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPSEED) >= 1 || this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.CAULIFLOWERSEED) >= 1){
            Dijkstra dijkstra = new Dijkstra(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCaseJoueur());
            Case caseTerreVide = null;
            int distanceMinimale = -1;
            for (Case c : this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCarte().getCases()) {
                if(c.getType() == TypeCase.TERRE && c.getObjet() == null){
                    if(caseTerreVide == null ||  dijkstra.getDistance(c) < distanceMinimale){
                        caseTerreVide = c;
                        distanceMinimale = dijkstra.getDistance(c);
                    }
                }
            }
            if(caseTerreVide != null){
                this.seDeplacerEn(caseTerreVide.getCoordonnee());
                if(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPSEED) >=1 ){
                    this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionPlanter(TypeRessource.PARSNIPSEED));
                }else if(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getQuantiteRessource(TypeRessource.CAULIFLOWERSEED) >=1 ) 
                    this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionPlanter(TypeRessource.CAULIFLOWERSEED));
                aPlante = true;
            }
        }
        return null;
    }
}

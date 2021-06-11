/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.cases.Case;
import ia.base.metier.objets.Plante;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public class EtatAllerRecolter extends Etat{

    private boolean aRecolte;
            
    public EtatAllerRecolter(Automate automate) {
        super(automate);
        this.aRecolte = false;
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        if(this.aRecolte){
            etat = new EtatCheckAction(this.getAutomate());
        }else{
            etat = new EtatAcheter(this.getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
        dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseARecolte = null;
        int distanceMinimale = -1;
        for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
            for (Plante p : getAutomate().getModuleMemoire().getListePlantes()) {
                if(c == p.getPosition() && p.estMature()){
                    if(caseARecolte == null || dijkstra.getDistance(p.getPosition()) < distanceMinimale){
                        caseARecolte = c;
                        distanceMinimale = dijkstra.getDistance(p.getPosition());
                    }
                }
            }
        }
        if(caseARecolte != null){
            seDeplacerEn(caseARecolte.getCoordonnee());
            getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionCueillir());
            this.aRecolte = true;
        }
        return null;
    }
    
}

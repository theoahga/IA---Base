/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import static ia.base.metier.carte.ressources.TypeRessource.EAU;
import ia.base.metier.cases.Case;
import ia.base.metier.cases.TypeCase;
import ia.base.metier.objets.Plante;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author clere
 */
public class EtatAllerArroser extends Etat {

    private boolean aArrose;
    
    public EtatAllerArroser(Automate automate) {
        super(automate);
        this.aArrose = false;
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        if(this.aArrose){
            etat = new EtatCheckAction(this.getAutomate());
        }else{
            if(this.getAutomate().getModuleMemoire().getQuantiteRessource(EAU) == 0){
                etat = new EtatAllerRemplir(this.getAutomate());
            }else{
                etat = new EtatAllerDormir(this.getAutomate());
            } 
        }
        return etat;
    }

    @Override
    public Action action(){ 
        if(this.getAutomate().getModuleMemoire().getQuantiteRessource(EAU)>0){
            Dijkstra dijkstra = new Dijkstra(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCarte());
            dijkstra.calculerDistancesDepuis(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCaseJoueur());
            Case caseAArroser = null;
                int distanceMinimale = -1;
                for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()) {
                    for (Plante p : getAutomate().getModuleMemoire().getListePlantes()) {
                        if(c == p.getPosition() && !p.isEstArrosee()){
                            int distance = dijkstra.getDistance(p.getPosition());
                            if(caseAArroser == null || distance < distanceMinimale){
                                caseAArroser = c;
                                distanceMinimale = distance;
                            }
                        }
                    }
                }
                if(caseAArroser != null){
                    aArrose = true;
                    this.seDeplacerEn(caseAArroser.getCoordonnee());
                    this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionArroser());
                }
        }
        return null;
    }
        
    
    
}

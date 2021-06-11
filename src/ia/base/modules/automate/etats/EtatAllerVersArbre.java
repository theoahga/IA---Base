/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.cases.Case;
import static ia.base.metier.objets.TypeObjet.ARBRE;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.actions.TypeActionRecolte;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public class EtatAllerVersArbre extends Etat{
    private boolean arbreTrouve;

    public EtatAllerVersArbre(Automate automate) {
        super(automate);
        this.arbreTrouve = false;
    }

    @Override
    public Etat transition() {
        Etat e = null;
        if(!this.arbreTrouve){
            e = new EtatAllerDormir(this.getAutomate());
        }else{
            e = new EtatCheckAction(this.getAutomate());
        }
        return e;
    }

    @Override
    public Action action() {
        Dijkstra d = new Dijkstra(this.getAutomate().getModuleMemoire().getCarte());
        d.calculerDistancesDepuis(this.getAutomate().getModuleMemoire().getCaseJoueur());
        Case caseAvecArbreLaPlusProche = null;
        int distanceMinimale = -1;
        for(Case c : this.getAutomate().getModuleMemoire().getCarte().getCases()){
            if ((c.getObjet() != null) && (c.getObjet().getType() == ARBRE)){
                if ((caseAvecArbreLaPlusProche == null) ||(d.getDistance(c)<distanceMinimale)){
                    caseAvecArbreLaPlusProche = c;
                    distanceMinimale = d.getDistance(c);
                }
            }
        }
        if (caseAvecArbreLaPlusProche != null){
            this.arbreTrouve = true ; 
            this.seDeplacerEn(caseAvecArbreLaPlusProche.getCoordonnee());
            if(!this.getAutomate().getListeDesActionArealiser().isEmpty()){
                Action action = this.getAutomate().getListeDesActionArealiser().get(this.getAutomate().getListeDesActionArealiser().size()-1);
                this.getAutomate().getListeDesActionArealiser().remove(this.getAutomate().getListeDesActionArealiser().size()-1);
                this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE,action.getDirection()));
                this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.getDirection()));
            }
        }
        return null;
    }
    
}

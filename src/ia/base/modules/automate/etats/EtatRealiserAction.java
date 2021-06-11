/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.cases.Case;
import static ia.base.metier.objets.TypeObjet.ARBRE;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import static metier.actions.TypeAction.MOUVEMENT;
import metier.actions.TypeActionRecolte;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public class EtatRealiserAction extends Etat{

    public EtatRealiserAction(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatCheckAction(this.getAutomate());
        return e;
    }

    @Override
    public Action action() {
        Action actionf = this.getAutomate().getListeDesActionArealiser().get(0);
        if (actionf.getType() == MOUVEMENT){
            Coordonnee coordonneDestination = this.getAutomate().getModuleMemoire().getJoueur().getCoordonnee().getVoisin(actionf.getDirection());
            Case caseDestination = this.getAutomate().getModuleMemoire().getCarte().getCase(coordonneDestination);
            if(caseDestination.getObjet() != null){
                if(caseDestination.getObjet().getType() == ARBRE){
                    this.getAutomate().getListeDesActionArealiser().add(0, FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE, actionf.getDirection()));
                    this.getAutomate().getListeDesActionArealiser().add(0, FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE, actionf.getDirection()));
                }
            }
        }
        actionf = this.getAutomate().getListeDesActionArealiser().get(0);
        this.getAutomate().getListeDesActionArealiser().remove(0);
        return actionf;
    }
    
}

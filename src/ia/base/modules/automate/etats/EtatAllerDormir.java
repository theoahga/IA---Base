/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.actions.TypeActionStatique;

/**
 *
 * @author theo clere
 */
public class EtatAllerDormir extends Etat{

    public EtatAllerDormir(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatCheckAction(this.getAutomate());
        return e;
    }

    @Override
    public Action action() {
        this.seDeplacerEn(this.getAutomate().getModuleMemoire().getCarte().getCoordonneeDepart());
        this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionStatique(TypeActionStatique.DORMIR));
        return null;
    }
    
}

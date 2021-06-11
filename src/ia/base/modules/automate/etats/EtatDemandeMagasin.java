/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.ActionDemande;

/**
 *
 * @author theo clere
 */
public class EtatDemandeMagasin extends Etat{

    public EtatDemandeMagasin(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(this.getAutomate());
        return etat;
    }

    @Override
    public Action action() {
        Action action = new ActionDemande("STORE");
        return  action;
    }
    
}

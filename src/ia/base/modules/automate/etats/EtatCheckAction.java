/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;

/**
 *
 * @author theo clere
 */
public class EtatCheckAction extends Etat {

    public EtatCheckAction(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat e = null;
        if(this.getAutomate().getListeDesActionArealiser().isEmpty()){
            e = new EtatAllerRecolter(this.getAutomate());
        }else{
            e = new EtatRealiserAction(this.getAutomate());
        }
        return e;
    }

    @Override
    public Action action() {
        return null;
    }
    
}

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
public class EtatCheckCarte extends Etat {

    public EtatCheckCarte(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition(){
        Etat a = null;
        if(this.getAutomate().getModuleMemoire().getCarte() != null){
            a = new EtatCheckAction(this.getAutomate());
        }else{
            a = new EtatDemanderCarte(this.getAutomate()); 
        }
        return a;
    }

    @Override
    public Action action() {
        return null;
    }
    
}

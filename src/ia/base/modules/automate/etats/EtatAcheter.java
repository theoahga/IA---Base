/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.metier.cases.Case;
import ia.base.metier.cases.CaseHerbe;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;
import metier.actions.Action;
import metier.actions.FabriqueAction;
import metier.algorithmes.Dijkstra;

/**
 *
 * @author theo clere
 */
public class EtatAcheter extends Etat{
    
    private boolean vaAcheter = false;

    public EtatAcheter(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = null;
        if(!this.getAutomate().getModuleMemoire().hasStockMagasin()){
            etat = new EtatDemandeMagasin(this.getAutomate());
        }else{
            if(this.vaAcheter){
                etat = new EtatCheckAction(this.getAutomate());
            }else{
                etat = new EtatAllerPlanter(this.getAutomate());
            }  
        }
        return etat;
    }

    @Override
    public Action action() {
       if(getAutomate().getModuleMemoire().hasStockMagasin()){
            if(getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 20){
                if(getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1 ){
                    Dijkstra dijkstra = new Dijkstra(getAutomate().getModuleMemoire().getCarte());
                    dijkstra.calculerDistancesDepuis(getAutomate().getModuleMemoire().getCaseJoueur());
                    Case MagasinGauche = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(0));
                    Case MagasinDroite = new CaseHerbe(getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(1));
                    Case caseMagasinPlusProche = null;
                    int distanceMinimale = -1;
                    for (Case c : getAutomate().getModuleMemoire().getCarte().getCases()){
                        if(c.getCoordonnee().equals(MagasinGauche.getCoordonnee()) || c.getCoordonnee().equals(MagasinDroite.getCoordonnee())){
                            if(caseMagasinPlusProche == null || dijkstra.getDistance(c) < distanceMinimale){
                                caseMagasinPlusProche = c;
                                distanceMinimale = dijkstra.getDistance(c);
                            }
                        }
                    }
                    this.vaAcheter = true;
                    this.seDeplacerEn(caseMagasinPlusProche.getCoordonnee());
                    if(this.getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED)>=1){
                        this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionAcheter(TypeRessource.PARSNIPSEED));
                    }else{
                        this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionAcheter(TypeRessource.CAULIFLOWERSEED));
                    } 
                        
                    
                }
            }
                    
        }
       return null;
    }
}



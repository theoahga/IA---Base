/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.carte.ressources.TypeRessource.EAU;
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
public class EtatAllerRemplir extends Etat{

    public EtatAllerRemplir(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(this.getAutomate());
        return etat;
    }

    @Override
    public Action action() {
        Dijkstra dijkstra = new Dijkstra(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCarte());
        dijkstra.calculerDistancesDepuis(this.getAutomate().getModuleDecision().getIA().getModuleMemoire().getCaseJoueur());
        Case caseBordDeLEauLaPlusProche = null;
        int distanceMinimale = -1;
        for(Case c :this.getAutomate().getModuleMemoire().getCarte().getCases()){
            if(c.estAccessible()){
                boolean bordDeLEau = false;
                for(Case v : c.getVoisins()){
                    if(v.getType() == TypeCase.EAU){
                        bordDeLEau = true;
                    }
                }
                if(bordDeLEau == true){
                    if(caseBordDeLEauLaPlusProche == null || dijkstra.getDistance(c)<distanceMinimale){
                        caseBordDeLEauLaPlusProche = c;
                        distanceMinimale = dijkstra.getDistance(c);
                    }
                }
            }
        }
        this.seDeplacerEn(caseBordDeLEauLaPlusProche.getCoordonnee());
        TypeMouvement direction = null; 
        for(Case v : caseBordDeLEauLaPlusProche.getVoisins()){
            if(v.getType() == TypeCase.EAU){
                direction = caseBordDeLEauLaPlusProche.getMouvementPourAller(v);
            }
        }
        this.getAutomate().getListeDesActionArealiser().add(FabriqueAction.creerActionRemplir(direction));
        return null;
    }
    
}

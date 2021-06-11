/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 *
 * @author theo cleres
 */
public class FabriqueAction {
    
    public static Action creerMouvement(TypeMouvement mouvement){
        return new ActionMouvement(mouvement);
    }
    
    public static Action creerDemande(TypeDemande demande){
        ActionDemande ActionD = null;
        switch(demande){
            case CARTE:
                ActionD = new ActionDemande("MAP");
                break;
            case MAGASIN:
                ActionD = new ActionDemande("STORE");
                break;
        }
        return ActionD;
    }
    
    public static Action creerActionStatique(TypeActionStatique type){
        Action actionStat =null;
        switch(type){
            case DORMIR:
                actionStat = new ActionDormir();
                break;
        }
        return actionStat;
    }
    
    public static Action creerActionRecolte(TypeActionRecolte type, TypeMouvement direction){
        Action actionRecolte = null;
        switch(type){
            case COUPERARBRE:
                actionRecolte = new ActionCouperArbre(direction);
                break;
        }
        return actionRecolte;
    }
    
    public static Action creerActionAcheter(TypeRessource typeRessource){
        Action a = new ActionAcheter(typeRessource);
        return a;
    }
    
    public static Action creerActionPlanter(TypeRessource typeRessource){
        Action a = new ActionPlanter(typeRessource);
        return a;
    }
    
    public static Action creerActionArroser(){
        Action a = new ActionArroser();
        return a;
    }
    
    public static Action creerActionRemplir(TypeMouvement type){
        Action a = new ActionRemplir(type);
        return a;
    }
    
    public static Action creerActionCueillir(){
        Action a = new ActionCueillir();
        return a;
    }
}
